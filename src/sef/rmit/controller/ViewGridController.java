/*
 * @author:
 */

package sef.rmit.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sef.rmit.custom.Grid;
import sef.rmit.util.Util;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ViewGridController implements Initializable
{

	//Intializing the columns that need to be displayed in the table view
	private TableColumn gridStatusCol = new TableColumn( "Grid Status" );
	private TableColumn gridNameCol = new TableColumn( "Grid Name" );
	//Creating a obseravble list of the data to be displayed to a user
	ObservableList<Grid> gridData = FXCollections.observableArrayList();
    
	@FXML
    private TableView<Grid> tableView;

    @FXML
    private Label lblStatus;
    
    @FXML
    private JFXButton cancelButton;

    /*
     * 
     * @parameters: ActionEvent Object
     * @throws Exception
     * @returns NULL
     */
    @FXML
    void redirectToDashBoard(ActionEvent event) throws Exception 
    {
    	//Setting outfocus event to save all data to DB
    	cancelButton.setFocusTraversable(true);
    	if(Grid.checkIfDefaultGridAlreadyExists())
    	{
    		Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
    	}
    	else
    	{
    		lblStatus.setText("Set only one default Grid");
    	}
    }

    /*
     * 
     * @parameters: Grid Status
     * @returns NULL
     * @throws Exception
     * @function: Adds all the grid data to be displayed in the observable list
     */
	public void getAllGridDataBasedOnUser(String gridStatus) throws Exception
	{
		//Get an arraylist of the grids using grid status
		ArrayList GridArrayList = Grid.getAllGridsByStatus(gridStatus);
		for(int incre = 0; incre < GridArrayList.size(); incre++)
		{
			Grid gridObj = (Grid) GridArrayList.get(incre);
			if(!Util.isNullorEmpty(gridObj.getGridID()))
			{
				//Setting a static Combo box values to the gridObj
				gridObj.setColumn1List(FXCollections.<String>observableArrayList( "ACTIVE", "INACTIVE", "DEFAULT" ));
				//Adding the gridobj to the observable list
				gridData.add(gridObj);
			}
		} 
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		try
		{
        	String gridStatus = "";
        	//Checking if edit permissions are allowed or denied based on User Rolw
	        if(LoginController.getLoggedInData().getUserRole().equals("ADMIN"))
	        {
	        	tableView.setEditable(true);
	        }
	        else
	        {
	        	gridStatus = "ACTIVE";
	        }
			lblStatus.setText("Select a Grid");
			//Setting width for all the columns in the table view
	        gridNameCol.setMinWidth(150);
	        gridStatusCol.setMinWidth(150);
	        //invoking method Get all the grid data based on the grid status and store it in obseravble list
			getAllGridDataBasedOnUser(gridStatus);
			//Creating a new combox box for the UI
	        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>()
	        {
	            @Override
	            public TableCell call(TableColumn col)
	            {
	                return new ComboBoxCell();
	            }
	        };
	        //Setting the values to be displayed from the observable list in the columns
	        gridNameCol.setCellValueFactory( new PropertyValueFactory<Grid, String>( "gridName" ) );
	        gridStatusCol.setCellValueFactory( new PropertyValueFactory<Grid, String>( "gridStatus" ) );
	        gridStatusCol.setCellFactory(cellFactory);
	        gridStatusCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Grid, String>>()
                {
                    @Override
                    public void handle( TableColumn.CellEditEvent<Grid, String> tableCol )
                    {
                    	try
                    	{
	                    	((Grid)tableCol.getTableView().getItems().get(tableCol.getTablePosition().getRow()))
	                    	.setStatusColumn(tableCol.getNewValue());
                    	}
                    	catch(Exception exp)
                    	{
                    		lblStatus.setText("Something Went Wrong!");
                    	}
                    }
                }
	        );
	        //Setting all the observable data to the tableView
			tableView.setItems(gridData);
			//Adding all the columns to the tableView
			tableView.getColumns().addAll(gridNameCol, gridStatusCol);
		}	
		catch(Exception arrExp)
		{
			lblStatus.setText("Something Went Wrong!");
		}	
		
	}
	
	/*
	 * 
	 * @Parameters: MouseEvent Object
	 * @throws Exception
	 * @returns: NULL
	 * @function: Opens the selected GRID UI
	 * 
	 */
	public void gridViewKeyEvent(MouseEvent eventObj) throws Exception
	{
		try
		{
			if(LoginController.getLoggedInData().getUserRole().equals("USER"))
			{
				lblStatus.setText("Select a Grid");
				TableViewSelectionModel selectionModel = tableView.getSelectionModel();
		        //Casting the tableview row to Grid object
		        Grid gridObj = (Grid) selectionModel.getSelectedItem();
				if(gridObj != null)
				{
					//Purwa's method to be called
					UserGridController.displayGrid(gridObj);
				}
				else
				{
					lblStatus.setText("Error during Grid Generation.");
				}
			}
			else
			{
				//lblStatus.setText("Action not permitted.");
			}
		}
		catch(Exception exp)
		{
			//lblStatus.setText("Connection Failed.");
		}
	}

	
	/*
	 * 
	 * functions: Creates a combox box for the column in the tableView
	 * 
	 */
    class ComboBoxCell extends TableCell<Grid, String>
    {

        private ComboBox<String> comboBox;

        public ComboBoxCell()
        {
            comboBox = new ComboBox<>();
        }

        @Override
        public void startEdit()
        {
            if (!isEmpty())
            {
                super.startEdit();
                comboBox.setItems( getTableView().getItems().get( getIndex() ).getColumn1List() );
                comboBox.getSelectionModel().select( getItem() );
                comboBox.focusedProperty().addListener( new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                    {
                    	try
                    	{
	                        if (!newValue)
	                        {
	                        	Grid gridObj = getTableView().getItems().get( getIndex() );
	                        	try
	                        	{
		                        	if(!gridObj.getGridStatus().equals(comboBox.getSelectionModel().getSelectedItem()))
		                        	{
		                        		//setting updated values to the grid object
		                        		gridObj.setLastModifiedUser(LoginController.getLoggedInData().getGivenName());
		                        		gridObj.setGridStatus(comboBox.getSelectionModel().getSelectedItem());
		                        		Grid.updateAdminGrid(gridObj);
			                        	gridData.remove(getIndex());
		                        		gridData.add(getIndex(), gridObj);
		                    			tableView.setItems(gridData);
		                        	}
		                            commitEdit(comboBox.getSelectionModel().getSelectedItem());
	                        	}
	                        	catch(Exception exp)
	                        	{
	                        		lblStatus.setText("Status Update Failed!");
	                        	}
	                        }
                    	}
                    	catch(Exception exp)
                    	{
                    		lblStatus.setText("Something Went Wrong!");
                    	}
                    }
                } );
                setText(null);
                setGraphic(comboBox);
            }
        }

        @Override
        public void updateItem( String item, boolean empty )
        {
            super.updateItem(item, empty);
            if (empty)
            {
                setText(null);
                setGraphic(null);
            }
            else
            {
                if (isEditing())
                {
                    setText(null);
                    setGraphic(comboBox);
                }
                else
                {
                    setText(getItem());
                    setGraphic(null);
                }
            }
        }

    }
}
