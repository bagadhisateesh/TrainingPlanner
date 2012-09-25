/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingplanner;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import trainingplanner.org.calendar.TrainingCalendarDay;
import trainingplanner.org.extensions.TrainingCalendarExt;

/**
 * FXML Controller class
 *
 * @author troutk
 */
public class TrainingPlannerCalendarController  extends AnchorPane implements Initializable {
    public static final String PROP_SELECTEDTRAININGDAY = "PROP_SELECTEDTRAININGDAY";
    @FXML private GridPane calendarGridPane;
    @FXML Text monthName;
    @FXML private Polygon nextMonthButton;
    @FXML private Polygon previousMonthButton;
    private GregorianCalendar calendar;
    private SimpleObjectProperty<TrainingCalendarDay> selectedTrainingDay;
    ObservableList<TrainingCalendarDay> calendarDays;
    private TrainingCalendarExt trainingCalendar;

    @FXML private Region firstDay;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (this.trainingCalendar == null) trainingCalendar = new TrainingCalendarExt();
        calendar = new GregorianCalendar();
        calendarDays = FXCollections.observableArrayList();
        //this.selectedTrainingDay = new SimpleObjectProperty<>();
        updateCalendar();
    }  
    
    @FXML private void nextMonth(){
        calendar.add(Calendar.MONTH, 1);
        updateCalendar();
    } 
    @FXML private void prevMonth(){
        calendar.add(Calendar.MONTH, -1);
        updateCalendar();
    } 

    public TrainingPlannerCalendarController(SimpleObjectProperty<TrainingCalendarDay> _selectedTrainingDay, TrainingCalendarExt _trainingCalendar){
            
        URL location = getClass().getResource("FXML/TrainingPlannerCalendar.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.selectedTrainingDay = _selectedTrainingDay;
        this.trainingCalendar = _trainingCalendar;
        
        nextMonthButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                nextMonth();
            }
        });
        
        previousMonthButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                prevMonth();
            }
        });
      updateCalendar();  
    }
    
    private TrainingCalendarDay[] setCalendar() {
        //String dateFormatString = "%1$tb %1$te, %1$tY";
        int dayObjectsCnt = 0; 
        TrainingCalendarDay[] dayObjects = new TrainingCalendarDay[42];    
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        int calFirstDay = calendar.get(Calendar.DAY_OF_WEEK);
        if(calFirstDay>1){
            //set last months days
            for (int lmd = calFirstDay-1; lmd>0; lmd --){
                Calendar lmdCal = Calendar.getInstance();
                lmdCal.setTime(calendar.getTime());
                int neglmd = 1-lmd;
                lmdCal.set(Calendar.DAY_OF_MONTH,neglmd);
                //TrainingCalendarDay calendarDay = trainingCalendar.getTrainingDay((GregorianCalendar)lmdCal));
                dayObjects[dayObjectsCnt] = trainingCalendar.getTrainingDay((GregorianCalendar)lmdCal);
                dayObjectsCnt++;
            }
        }
        //setall these months days
        int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int dayCounter = 0; dayCounter<totalDays; dayCounter++){
            //TrainingCalendarDay calendarDay = new TrainingCalendarDay();
            Calendar thisDay = Calendar.getInstance();
            thisDay.setTime(calendar.getTime());
            thisDay.add(Calendar.DAY_OF_MONTH, dayCounter);
            //calendarDay.setCalendar(thisDay);
            //calendarDay.setTrainingDay(trainingCalendar.getTrainingDay((GregorianCalendar)thisDay));
            dayObjects[dayObjectsCnt]=trainingCalendar.getTrainingDay((GregorianCalendar)thisDay);//calendarDay;
            if (dayObjectsCnt == 13){
                System.out.println("13"+dayObjects[dayObjectsCnt].getobservableWorkOuts().size());
            }
            dayObjectsCnt++;
        }
       //setRemeaingDays from next month
        int nextMonthDays = 0;
        while(dayObjectsCnt<42){
            //TrainingCalendarDay calendarDay = new TrainingCalendarDay();
            Calendar thisDay = Calendar.getInstance();
            thisDay.setTime(calendar.getTime());
            thisDay.add(Calendar.MONTH,1);
            thisDay.add(Calendar.DAY_OF_MONTH, nextMonthDays);
            //calendarDay.setCalendar(thisDay);
            //calendarDay.setTrainingDay(trainingCalendar.getTrainingDay((GregorianCalendar)thisDay));
            dayObjects[dayObjectsCnt]=trainingCalendar.getTrainingDay((GregorianCalendar)thisDay);
            nextMonthDays++;
            dayObjectsCnt++;  
        }
        return dayObjects;
    }

    private void updateCalendar() {
        //calendar.setTime(new Date());
        monthName.setText(String.format("%1$tY %1$tB", calendar));
        calendarDays.setAll(setCalendar());

        for(Object calObj : calendarGridPane.getChildren()){
            if (calObj instanceof Group){
                Group group = (Group) calObj;
                ObservableMap<Object, Object> properties = group.getProperties();
                int column = (Integer)properties.get("gridpane-column");
                int row = (Integer)properties.get("gridpane-row");
                int calNumber = ((row-1)*7)+column;
                TrainingCalendarDay trainingDay = calendarDays.get(calNumber);
                Calendar curCal = trainingDay.getCalendar();
                
                for(Object child : group.getChildren()){
                    if (child instanceof Text){
                        Text childText = (Text) child;
                        childText.setText(String.valueOf(curCal.get(Calendar.DAY_OF_MONTH)));
                        childText.getStyleClass().clear();
                        if (curCal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
                        {
                            childText.getStyleClass().add("monthDateBoxActiveText");
                        }
                        else
                        {
                            childText.getStyleClass().add("monthDateBoxInactiveText");
                        }
                    }
                    if (child instanceof Rectangle){
                        Rectangle childRectangle = (Rectangle) child;
                        childRectangle.getStyleClass().clear();
                        if (curCal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
                        {
                            childRectangle.getStyleClass().add("monthDateBoxActive");
                        }
                        else
                        {
                            childRectangle.getStyleClass().add("monthDateBoxInactive");
                        }
                    }
                }
            }
            if (calObj instanceof Region){
                Region region = (Region)calObj;
                region.getStyleClass().clear();
                region.setEffect(null);
                ObservableMap<Object, Object> properties = region.getProperties();
                int column = (Integer)properties.get("gridpane-column");
                int row = (Integer)properties.get("gridpane-row");
                String rowClass = (row % 2 == 0)? "evenWeek":"oddWeek"; 
                region.getStyleClass().add(rowClass);
                final int calNumber = ((row-1)*7)+column;
                TrainingCalendarDay trainingDay = calendarDays.get(calNumber);
                Calendar curCal = trainingDay.getCalendar();
                
                region.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        
                        selectedTrainingDay.set(calendarDays.get(calNumber));
                    }
                });
                
                //not this month so inactivate the region
                if (curCal.get(Calendar.MONTH) != calendar.get(Calendar.MONTH)){
                        region.getStyleClass().clear();
                        region.getStyleClass().add("inActiveWeek");
                    }
                //Today has a special Region code
                if (trainingDay.isToday()){
                   if (this.selectedTrainingDay == null) {
                       this.selectedTrainingDay = new SimpleObjectProperty<>(trainingDay);
                    } 
                    region.getStyleClass().clear();
                    region.getStyleClass().add("todayBox");
                    region.setEffect(new Glow(.2));
                }
                
            }
        }
    }

    /**
     * @return the selectedTrainingDay
     */
    public SimpleObjectProperty<TrainingCalendarDay> getSelectedTrainingDay() {
        return selectedTrainingDay;
    }
}
    /**
     * @param selectedTrainingDay the selectedTrainingDay to set
     */
