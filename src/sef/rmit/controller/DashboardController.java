/*
 * @author:
 */

package sef.rmit.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import sef.rmit.custom.Graph;
import sef.rmit.util.Util;

@SuppressWarnings({"rawtypes","unchecked"})
public class DashboardController implements Initializable
{   
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private LineChart<?, ?> activityGraph;
    
    @FXML
    private JFXButton button1;

    @FXML
    private JFXButton button2;
    
    @FXML
    private JFXButton button3;
    
    @FXML
    private Label lblStatus;
	
    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     * function: Used to initialize the Chart for User Activity
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		try
		{
			//Intialize the Chart series
			XYChart.Series series = new XYChart.Series();
			//Set a name for the series
			series.setName("User Activity");
			DateFormat outputFormat = new SimpleDateFormat("MMM");
			//Get all the graph objects from the DB
			ArrayList graphList= Graph.getAllGraphs();
			for(int incre = 0; incre < graphList.size(); incre++)
			{
				//Get each Graph Object and draw the chart
				Graph graphObj = (Graph) graphList.get(incre);
				if(LoginController.getLoggedInData().getUserRole().equals("ADMIN"))
				{
					//Setting data to the chart based on created Date
					String createdDate = outputFormat.format(graphObj.getCreatedDate());
					series.getData().add(new XYChart.Data<String, Number>(createdDate,  
							graphObj.getTotalGraphCount()));	
				}
				else if(LoginController.getLoggedInData().getUserRole().equals("USER"))
				{
					//getting createdDate from the graphObject
					String createdDate = outputFormat.format(graphObj.getCreatedDate());
					if(graphObj.getCreatedUser().equals(LoginController.getLoggedInData().getCreatedUser()))
					{
						//Setting the data to the chart based on Created Date and Created User
						series.getData().add(new XYChart.Data<String, Number>
						(createdDate,  graphObj.getTotalGraphCount()));
					}
				}
				else
				{
					//Display error in Case of DB Connection Failed
					lblStatus.setText("Connection Failed.");
				}
			}
			//Adding all the chart data to the FXGraph
			activityGraph.getData().add(series);
			//Check if logged in user is an ordinary USER
			if(LoginController.getLoggedInData().getUserRole().equals("USER"))
			{
				//Change buttons if User is not ADMIN
				button1.setText("Simulate Traffic");
				button2.setText("View History");
			}
		}
		catch (Exception exp) 
		{
			//Display error in case of DB Connection Failed
			lblStatus.setText("Connection Failed.");
		} 
	}
	
	/*
	 *
	 * @parameters: ActionEvent Object
	 * @throws Exception
	 * @function: Redirect to UpdateProfile UI
	 * @returns: NULL
	 */
	public void button3Action(ActionEvent event) throws Exception
	{
		Util.displayUX(UpdateProfileController.class, "fxml/UpdateProfile.fxml", null);
	}
	
	/*
	 *
	 * @parameters Action Event Object
	 * @function: redirect to View Admin Grid or View available grids based on role
	 * @returns: NULL
	 * @throws: Exception
	 */
	public void button1Action(ActionEvent event) throws Exception
	{
		//Check if logged in user is admin
		if(LoginController.getLoggedInData().getUserRole().equals("ADMIN"))
		{
			Util.displayUX(AdminGridController.class, "fxml/AdminGrid.fxml", null);	
		}
		//Check if logged in user is ordinary user
		else if(LoginController.getLoggedInData().getUserRole().equals("USER"))
		{
			Util.displayUX(ViewGridController.class, "fxml/ViewGrids.fxml", null);
		}
	}
	
	/*
	 *
	 * @throws Exception
	 * @parameters ActionEvent Object
	 * @returns NULL
	 * @function: Redirect to Edit Grids or View graphs based on Role
	 */
	public void button2Action(ActionEvent event) throws Exception
	{
		//Check if logged in user is admin
		if(LoginController.getLoggedInData().getUserRole().equals("ADMIN"))
		{
			Util.displayUX(ViewGridController.class, "fxml/ViewGrids.fxml", null);
		}
		//Check if logged in user is ordinary user
		else if(LoginController.getLoggedInData().getUserRole().equals("USER"))
		{
			Util.displayUX(ViewGraphController.class, "fxml/ViewGraphs.fxml", null);
		}
	}
	
	/*
	 *
	 * @throws Exception
	 * @returns NULL
	 * @function Redirect to Login UI after successful login
	 * @parameters; ActionEvent Object
	 */
	public void button4Action(ActionEvent event) throws Exception
	{
		Util.displayUX(LoginController.class, "fxml/Login.fxml", null);
	}
	
	
}
