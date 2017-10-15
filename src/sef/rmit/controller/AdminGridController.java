/*
 * @author:
 */

package sef.rmit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sef.rmit.custom.Grid;
import sef.rmit.util.Util;

@SuppressWarnings({"unchecked","rawtypes"})
public class AdminGridController implements Initializable 
{

	@FXML
	private Rectangle block1Hori;

	@FXML
	private Rectangle block1Vert;

	@FXML
    private Rectangle verticalRoad3;

    @FXML
    private Rectangle verticalRoad2;

    @FXML
    private Rectangle verticalRoad1;

    @FXML
    private Rectangle horizontalRoad3;

    @FXML
    private Rectangle horizontalRoad2;

    @FXML
    private Rectangle horizontalRoad1;
    
    @FXML
    private ImageView deleteHori1;

    @FXML
    private ImageView deleteHori2;

    @FXML
    private ImageView deleteHori3;
    
    @FXML
    private ImageView deleteVeri1;

    @FXML
    private ImageView deleteVeri2;

    @FXML
    private ImageView deleteVeri3;
    
    @FXML
    private ImageView addHori1;
    
    @FXML
    private ImageView addHori2;
    
    @FXML
    private ImageView addHori3;
    
    @FXML
    private ImageView addVeri1;
    
    @FXML
    private ImageView addVeri2;
    
    @FXML
    private ImageView addVeri3;
    
    @FXML
    private JFXButton buttonReset;
    
    @FXML
    private JFXButton buttonSave;
    
    @FXML
    private TextField inpVert1Side1;
    
    @FXML
    private TextField inpVerti1Side2;
    
    @FXML
    private TextField inpVeri2Side1;
    
    @FXML
    private TextField inpVeri2Side2;
    
    @FXML
    private TextField inpVeri3Side1;
    
    @FXML
    private TextField inpVeri3Side2;
    
    @FXML
    private TextField inpHorz1Side1;
    
    @FXML
    private TextField inpHorz1Side2;
    
    @FXML
    private TextField inpHorzi2Side1;
    
    @FXML
    private TextField inpHorzi2Side2;
    
    @FXML
    private TextField inpHorzi3Side1;
    
    @FXML
    private TextField inpHorzi3Side2;
    
    @FXML
    private JFXTextField inpSimulationTime;
    
    @FXML
    private Label lblStatus;
    
	@FXML
    private ChoiceBox inpGridStatus;
    
    @FXML
    private JFXTextField inpGridName;
   
    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//Hide all the Add road buttons in the Grid
		hideAllAddButtons();
		//Create an array of all the input textboxes
		TextField[] inputBoxArray = {inpVert1Side1,inpVerti1Side2,inpVeri2Side1,inpVeri2Side2,inpVeri3Side1,
				inpVeri3Side2,inpHorz1Side1,inpHorz1Side2,inpHorzi2Side1,inpHorzi2Side2,inpHorzi3Side1,inpHorzi3Side2};
		for(TextField textField: inputBoxArray)
		{
			//Add key press event to validate only Numeric values allowed
			Util.validateInput(textField, null, 2, true);
		}
		//Add key press event to validate only Numeric values allowed
		Util.validateInput(null, inpSimulationTime, 3, false);	
		//Setting values for choice box
		inpGridStatus.setItems(FXCollections.observableArrayList(
			    "ACTIVE", "DEFAULT","PLANNED")
			);
	}

	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Remove First Horizontal Road from Grid
	 */
	public void removeFirstHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad1.setFill(Color.RED);
		deleteHori1.setVisible(false);
		addHori1.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Remove Second Horizontal Road from Grid
	 */
	public void removeSecondHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad2.setFill(Color.RED);
		deleteHori2.setVisible(false);
		addHori2.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Remove Third Horizontal Road from Grid
	 */
	public void removeThirdHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad3.setFill(Color.RED);
		deleteHori3.setVisible(false);
		addHori3.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @functions: Remove First Vertical Road from Grid
	 */
	public void removeFirstVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad1.setFill(Color.RED);
		deleteVeri1.setVisible(false);
		addVeri1.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @functions: Remove Second Vertical Road from Grid
	 */
	public void removeSecondVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad2.setFill(Color.RED);
		deleteVeri2.setVisible(false);
		addVeri2.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @functions: Remove Third Vertical Road from Grid
	 */
	public void removeThirdVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad3.setFill(Color.RED);
		deleteVeri3.setVisible(false);
		addVeri3.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add First Horizontal Road in Grid
	 */
	public void addFirstHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad1.setFill(Color.BLACK);
		addHori1.setVisible(false);
		deleteHori1.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add Second Horizontal Road in Grid
	 */
	public void addSecondHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad2.setFill(Color.BLACK);
		addHori2.setVisible(false);
		deleteHori2.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add Third Horizontal Road in Grid
	 */
	public void addThirdHorizontalRoad(MouseEvent eventObj)
	{
		horizontalRoad3.setFill(Color.BLACK);
		addHori3.setVisible(false);
		deleteHori3.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add First Vertical Road in Grid
	 */
	public void addFirstVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad1.setFill(Color.BLACK);
		addVeri1.setVisible(false);
		deleteVeri1.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add Second Vertical Road in Grid
	 */
	public void addSecondVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad2.setFill(Color.BLACK);
		addVeri2.setVisible(false);
		deleteVeri2.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: MouseEvent Object
	 * @returns NULL
	 * @function: Add Third Vertical Road in Grid
	 */
	public void addThirdVerticalRoad(MouseEvent eventObj)
	{
		verticalRoad3.setFill(Color.BLACK);
		addVeri3.setVisible(false);
		deleteVeri3.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: ActionEvent Object
	 * @returns NULL
	 * @function: Store Grid Configuration in Db
	 */
	public void saveGridState(ActionEvent eventObj)
	{
		try
		{
			if(Util.isNullorEmpty(inpVert1Side1.getText()) || Util.isNullorEmpty(inpVerti1Side2.getText())
				|| Util.isNullorEmpty(inpVeri2Side1.getText()) || Util.isNullorEmpty(inpVeri2Side2.getText())
				|| Util.isNullorEmpty(inpVeri3Side1.getText()) || Util.isNullorEmpty(inpVeri3Side2.getText())
				|| Util.isNullorEmpty(inpHorz1Side1.getText()) || Util.isNullorEmpty(inpHorz1Side2.getText())
				|| Util.isNullorEmpty(inpHorzi2Side1.getText()) || Util.isNullorEmpty(inpHorzi2Side2.getText())
				|| Util.isNullorEmpty(inpHorzi3Side1.getText()) || Util.isNullorEmpty(inpHorzi3Side2.getText())
				|| Util.isNullorEmpty(inpSimulationTime.getText()) || Util.isNullorEmpty(inpGridName.getText()))
			{
				lblStatus.setText("    All Fields are Mandatory.");
			}
			else if(!Util.isNullorEmpty(inpGridStatus.getValue().toString()) 
					&& !(inpGridStatus.getValue().toString().equals("ACTIVE") 
							|| inpGridStatus.getValue().toString().equals("INACTIVE")
							|| inpGridStatus.getValue().toString().equals("DEFAULT")
							|| inpGridStatus.getValue().toString().equals("PLANNED")))
			{
					lblStatus.setText("  Invalid Grid Status.");
			}
			else if(!Util.isNullorEmpty(inpGridStatus.getValue().toString()) 
					&& (inpGridStatus.getValue().toString().equals("DEFAULT") && Grid.checkIfDefaultGridAlreadyExists()))
			{
				lblStatus.setText("Default Grid already exists.");
			}
			else
			{
				lblStatus.setText("");
				Grid gridObj = new Grid();
				gridObj.setGridID(formGridID());
				gridObj.setGridStatus(inpGridStatus.getValue().toString());
				gridObj.setGridSimulationTime(inpSimulationTime.getText());
				gridObj.setcreatedUser(LoginController.getLoggedInData().getGivenName());
				gridObj.setLastModifiedUser(LoginController.getLoggedInData().getGivenName());
				gridObj.setGridName(inpGridName.getText());
				gridObj.setRoad1Side1Value(inpVert1Side1.getText());
				gridObj.setRoad1Side2Value(inpVerti1Side2.getText());
				gridObj.setRoad2Side1Value(inpVeri2Side1.getText());
				gridObj.setRoad2Side2Value(inpVeri2Side2.getText());
				gridObj.setRoad3Side1Value(inpVeri3Side1.getText());
				gridObj.setRoad3Side2Value(inpVeri3Side2.getText());
				gridObj.setRoad4Side1Value(inpHorz1Side1.getText());
				gridObj.setRoad4Side2Value(inpHorz1Side2.getText());
				gridObj.setRoad5Side1Value(inpHorzi2Side1.getText());
				gridObj.setRoad5Side2Value(inpHorzi2Side2.getText());
				gridObj.setRoad6Side1Value(inpHorzi3Side1.getText());
				gridObj.setRoad6Side2Value(inpHorzi3Side2.getText());
				boolean isInserted = Grid.insertAdminGrid(gridObj);
				//Redirect to Dashboard based on ROLE after successful login
				if(isInserted)
				{
					Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
				}
			}
		}
		catch(Exception exp)
		{
			lblStatus.setText("Connection Failed.Try Later!");
		}
			
	}	
	
	/*
	 * 
	 * @parameter: ActionEvent Object
	 * @returns NULL
	 * @function return Gird State to Default
	 */
	public void resetGridState(ActionEvent eventObj)
	{
		lblStatus.setText("");
		horizontalRoad1.setFill(Color.BLACK);
		horizontalRoad2.setFill(Color.BLACK);
		horizontalRoad3.setFill(Color.BLACK);
		verticalRoad1.setFill(Color.BLACK);
		verticalRoad2.setFill(Color.BLACK);
		verticalRoad3.setFill(Color.BLACK);
		hideAllAddButtons();
		showAllDeleteButtons();
		voidResetAllInputFields();
	}
	
	/*
	 * 
	 * @parameter: NULL
	 * @returns NULL
	 * @function: Hide all Add road buttons
	 */
	public void hideAllAddButtons()
	{
		addHori1.setVisible(false);
		addHori2.setVisible(false);
		addHori3.setVisible(false);
		addVeri1.setVisible(false);
		addVeri2.setVisible(false);
		addVeri3.setVisible(false);
	}
	
	/*
	 * 
	 * @parameter: NULL
	 * @returns NULL
	 * @function: Show all Delete road buttons
	 */
	public void showAllDeleteButtons()
	{
		deleteHori1.setVisible(true);
		deleteHori2.setVisible(true);
		deleteHori3.setVisible(true);
		deleteVeri1.setVisible(true);
		deleteVeri2.setVisible(true);
		deleteVeri3.setVisible(true);
	}
	
	/*
	 * 
	 * @parameter: NULL
	 * @returns NULL
	 * @function: set values for all text boxes as empty
	 */
	public void voidResetAllInputFields()
	{
		inpVert1Side1.setText("");
		inpVerti1Side2.setText("");
		inpVeri2Side1.setText("");
		inpVeri2Side2.setText("");
		inpVeri3Side1.setText("");
		inpVeri3Side2.setText("");
		inpHorz1Side1.setText("");
		inpHorz1Side2.setText("");
		inpHorzi2Side1.setText("");
		inpHorzi2Side2.setText("");
		inpHorzi3Side1.setText("");
		inpHorzi3Side2.setText("");
	}
	
	/*
	 * 
	 * @parameter: Action event object
	 * @returns NULL
	 * @throws Exception
	 * @function: redirect user to dashboard UI
	 */
	public void redirectToDashBoard(ActionEvent evtObj) throws Exception
	{
		Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
	}
	
	public String formGridID()
	{
		StringBuilder gridIDCreator = new StringBuilder(6);
		if(addVeri1.isVisible())
		{
			gridIDCreator.insert(0, '1');
		}
		else
		{
			gridIDCreator.insert(0, '0');
		}
		if(addVeri2.isVisible())
		{
			gridIDCreator.insert(1, '1');
		}
		else
		{
			gridIDCreator.insert(1, '0');
		}
		if(addVeri3.isVisible())
		{
			gridIDCreator.insert(2, '1');
		}
		else
		{
			gridIDCreator.insert(2, '0');
		}
		if(addHori1.isVisible())
		{
			gridIDCreator.insert(3, '1');
		}
		else
		{
			gridIDCreator.insert(3, '0');
		}
		if(addHori2.isVisible())
		{
			gridIDCreator.insert(4, '1');
		}
		else
		{
			gridIDCreator.insert(4, '0');
		}
		if(addHori3.isVisible())
		{
			gridIDCreator.insert(5, '1');
		}
		else
		{
			gridIDCreator.insert(5, '0');
		}	
		return gridIDCreator.toString();
	}
}