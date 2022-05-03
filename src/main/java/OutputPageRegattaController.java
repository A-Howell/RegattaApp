import classes.Crew;
import classes.CrewMember;
import classes.Race;
import classes.Regatta;
import com.itextpdf.text.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import com.itextpdf.text.pdf.PdfWriter;


public class OutputPageRegattaController implements Initializable {
    @FXML
    private TextField raceDayInfoFileNameBox;
    @FXML
    private TextField finishTimesInfoFileNameBox;

    @FXML
    private Button raceDayInfoButton;
    @FXML
    private Button finishTimesInfoButton;


    private final CreationPageController parentController;

    public OutputPageRegattaController(CreationPageController parentController) {
        this.parentController = parentController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        String fileName = r.getName().replace(" ", "").toLowerCase()
                + r.getDate().getDayOfMonth() // TODO add validation on regatta creation
                + r.getDate().getMonth().getValue()
                + r.getDate().getYear();

        this.raceDayInfoFileNameBox.setText(fileName + "_RaceDayInfo");
        this.finishTimesInfoFileNameBox.setText(fileName + "_FinishTimes");

        int i = 0;
        for (Race race : r.getRaces()) {
            if (race.isFinished()) {
                i++;
            }
        }

        boolean areRacesFinished = !(i == r.getRaceCount());
        this.finishTimesInfoFileNameBox.setDisable(areRacesFinished);
        this.finishTimesInfoButton.setDisable(areRacesFinished);

    }

    // Button actions

    @FXML
    private void raceDayInfoButtonAction(ActionEvent event) throws FileNotFoundException, DocumentException {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        String fileName = this.raceDayInfoFileNameBox.getText() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 24, BaseColor.BLACK);
        Font raceFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
        Font crewFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

        String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(r.getDate());
        Paragraph p = new Paragraph(r.getName() + " - " + r.getLocation() + " - " + date, titleFont);
        document.add(p);

        p = new Paragraph("Today's timings", titleFont);
        document.add(p);

        for (Race race : r.getRaces()) {
            p = new Paragraph(race.toStringForPDF(), raceFont); // 11:34 |
            document.add(p);

            List<Crew> crewsFromIDs = new ArrayList<>();
            for (String crewID : race.getCrewList()) {
                crewsFromIDs.add(r.getCrewByID(crewID));
            }
            String crewStr = "";
            for (Crew crew : crewsFromIDs) { //     [C1] Worthing RC (A.Howell, M.Browning.)
                 crewStr += crew.getCrewID() + " "
                        + r.getTeamByID(crew.getTeamID()).getTeamName() + " (";

                List<CrewMember> crewMembersFromIDs = new ArrayList<>();
                for (String crewMemberID : crew.getCrewMembersID()) {
                    crewMembersFromIDs.add(r.getCrewMemberByID(crewMemberID));
                }

                for (int i = 0; i < crewMembersFromIDs.size(); i++) {
                    crewStr += crewMembersFromIDs.get(i).getShortFullName();
                    if (i != crewMembersFromIDs.size() - 1) {
                         crewStr += ", ";
                    }
                }
                crewStr += ")    ";

            }
            p = new Paragraph(crewStr, crewFont);
            document.add(p);
        }

        document.close();
    }

    @FXML
    private void finishTimesInfoButtonAction(ActionEvent event) throws DocumentException, FileNotFoundException {
        Stage stage = (Stage) this.parentController.getBorderPane().getScene().getWindow();
        Regatta r = (Regatta) stage.getUserData();

        String fileName = this.finishTimesInfoFileNameBox.getText() + ".pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 24, BaseColor.BLACK);
        Font raceFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
        Font crewFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

        String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(r.getDate());
        Paragraph p = new Paragraph(r.getName() + " - " + r.getLocation() + " - " + date, titleFont);
        document.add(p);

        p = new Paragraph("Final race times", titleFont);
        document.add(p);

        for (Race race : r.getRaces()) {
            String raceStr = race.toStringForPDF() + " | Winner: ";
            raceStr += r.getTeamByID(r.getCrewByID(race.getWinnerCrewID()).getTeamID()).getTeamName();
            p = new Paragraph(raceStr, raceFont); // 11:34 |
            document.add(p);

            List<Crew> crewsFromIDs = new ArrayList<>();
            for (String crewID : race.getCrewList()) {
                crewsFromIDs.add(r.getCrewByID(crewID));
            }
            for (Crew crew : crewsFromIDs) { //     [C1] Worthing RC (A.Howell, M.Browning.)
                String crewStr = "";
                crewStr += crew.getCrewID() + " "
                        + r.getTeamByID(crew.getTeamID()).getTeamName() + " (";

                List<CrewMember> crewMembersFromIDs = new ArrayList<>();
                for (String crewMemberID : crew.getCrewMembersID()) {
                    crewMembersFromIDs.add(r.getCrewMemberByID(crewMemberID));
                }

                for (int i = 0; i < crewMembersFromIDs.size(); i++) {
                    crewStr += crewMembersFromIDs.get(i).getShortFullName();
                    if (i != crewMembersFromIDs.size() - 1) {
                        crewStr += ", ";
                    }
                }
                crewStr += ") - ";

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss.SSS");

//                crewStr += dtf.format(race.getActualRaceStartTime().until(race.getCrewFinishTimes().get(crew.getCrewID())), ChronoUnit.MILLIS);
                LocalTime startTime = race.getActualRaceStartTime();
                LocalTime crewFinishTime = race.getCrewFinishTimes().get(crew.getCrewID());

                long millis = ChronoUnit.MILLIS.between(startTime, crewFinishTime);

                String timeStr = String.format("%02d:%02d.%03d",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)), // The change is in this line
                        TimeUnit.MILLISECONDS.toMillis(millis) -
                                TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis)));

                crewStr += timeStr;

                /*crewStr += ChronoUnit.MINUTES.between(startTime, crewFinishTime) + ":"
                    + ChronoUnit.SECONDS.between(startTime, crewFinishTime) + "."
                    ;*/

                p = new Paragraph(crewStr, crewFont);
                document.add(p);
            }

        }
        document.close();

    }

    // ListView Actions


    // Setters


    // Getters

}
