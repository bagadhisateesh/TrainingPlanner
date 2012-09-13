/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingplanner;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import trainingplanner.controls.DailyCountDownController;
import trainingplanner.controls.PaceClockController;
import trainingplanner.controls.SpeedOMeterController;
import trainingplanner.org.calendar.TrainingCalendarDay;
import trainingplanner.org.extensions.AthleteExt;
import trainingplanner.org.extensions.TrainingPlanExt;

/**
 *
 * @author troutk
 */
public class TrainingPlannerWindowController implements Initializable {
    
    private String dateFormatString = "%1$tb %1$te, %1$tY";
    private SimpleObjectProperty<Color> color = new SimpleObjectProperty<>(this, "color", Color.LIME);
    
    private TrainingPlanExt trainingPlan;
    private AthleteExt athlete;
    
    @FXML  private Label athleteName;
    @FXML  private Label athleteAge;
    @FXML  private Label athleteDOB;
    @FXML  private Label athleteWeight;
    @FXML  private Label todaysDate;
    @FXML  private AnchorPane dashBoardPane;
    @FXML  private StackPane goalsPane;
    @FXML  private StackPane calendarPane;
    @FXML  private FlowPane goalIcons;
    @FXML  private ColorPicker colorPicker;
    private SimpleObjectProperty<TrainingCalendarDay> selectedCalendarDate;
    
    
    
    @FXML
    private void quitAction(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCalendarDate = new SimpleObjectProperty<>();
        selectedCalendarDate.addListener(new ChangeListener<TrainingCalendarDay>() {

            @Override
            public void changed(ObservableValue<? extends TrainingCalendarDay> ov, TrainingCalendarDay t, TrainingCalendarDay t1) {
                System.out.println("Day Selected: "+t1.getCalendar());
            }
        });
        todaysDate.setText(String.format(dateFormatString, new GregorianCalendar()));
        trainingPlan = new TrainingPlanExt();
        trainingPlan.initialize();
        athlete = trainingPlan.getAthlete();
        athleteName.setText(athlete.getFullName());
        athleteAge.setText(String.valueOf(athlete.getAge()));
        athleteWeight.setText(String.valueOf(athlete.getWeight()));
        athleteDOB.setText(String.format(dateFormatString,athlete.getDateOfBirth().toGregorianCalendar()));
        colorPicker.valueProperty().setValue(Color.LIME);
        
        calendarPane.getChildren().clear();
        TrainingPlannerCalendarController calendar = new TrainingPlannerCalendarController(selectedCalendarDate);
        calendarPane.getChildren().add(calendar);
        
        URL location = getClass().getResource("FXML/TrainingPlannerGoals.fxml");
        FXMLLoader goalsLoader = new FXMLLoader();
        goalsLoader.setLocation(location);
        goalsLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent goalsPaneParent;
            
        try {
            goalsPaneParent = (Parent) goalsLoader.load(location.openStream());          
            goalsPane.getChildren().clear();
            goalsPane.getChildren().add(goalsPaneParent);  
            
        } catch (IOException ex) {
            Logger.getLogger(TrainingPlannerWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        color.bind(colorPicker.valueProperty());

        TrainingPlannerGoalsController goalsControler = goalsLoader.getController();
        goalsControler.setKPIs(athlete.getKeyPerformanceIndicators());
        goalsControler.setGoals();

        ObservableList<Node> goals = goalIcons.getChildren();
        goals.clear();
        SpeedOMeterController bikeSpeed = new SpeedOMeterController();
        bikeSpeed.setSpeed(42.0);
        bikeSpeed.setScaleX(0.5);
        bikeSpeed.setScaleY(0.5);
        goalIcons.getChildren().add(bikeSpeed);
        
        PaceClockController paceClock = new PaceClockController(new SimpleIntegerProperty(537), color);
        paceClock.setScaleX(0.5);
        paceClock.setScaleY(0.5);
        goalIcons.getChildren().add(paceClock);
        
        DailyCountDownController counter = new DailyCountDownController(new SimpleObjectProperty<>(new GregorianCalendar(2013, 7, 18) ), color);
        goalIcons.getChildren().add(counter);
        goalIcons.setAlignment(Pos.CENTER);
    }    
}
