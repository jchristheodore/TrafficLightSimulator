/*
 * @author:
 */

package sef.rmit.controller;

import java.net.URL;
import java.util.ResourceBundle;
import sef.rmit.custom.*;
import sef.rmit.util.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UpdateProfileController implements Initializable 
{	

	@FXML
    private JFXTextField txtGivenname;
	
    @FXML
    private JFXTextField txtFamilyName;

    @FXML
    private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtUsername;
	
	@FXML
	private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtCnfPassword;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton updateButton;
    
    @FXML
    private JFXButton continueButton;

    @FXML
    private ImageView editButton;
    
    @FXML
    private Label lblStatus;
	
    /*
     * (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		disable();
	}
	
	/*
	 *
	 * @parameters" Mouse event Object
	 * @function: enables all the input controls for user to edit
	 * @returns NULL
	 */
	public void editProfile(MouseEvent event)
	{
		txtCnfPassword.setDisable(false);
		txtPassword.setDisable(false);
		updateButton.setDisable(false);
		txtGivenname.setEditable(true);
		txtFamilyName.setEditable(true);
		txtEmail.setEditable(true);
		updateButton.setVisible(true);
		txtGivenname.setFocusTraversable(true);
	}

	/*
	 *
	 * @parameters: NULL
	 * @return NULL
	 * @function setting default values of the user in the input controls
	 */
	public void disable()
	{
		//Setting values for the user from the global user object after Login
		txtGivenname.setText(LoginController.getLoggedInData().getGivenName());
		txtFamilyName.setText(LoginController.getLoggedInData().getFamilyName());
		txtEmail.setText(LoginController.getLoggedInData().getEmailAddress());
		txtPassword.setText(LoginController.getLoggedInData().getPassword());
		txtCnfPassword.setText(LoginController.getLoggedInData().getPassword());
		txtEmail.setEditable(false);
		txtPassword.setDisable(true);
		txtCnfPassword.setDisable(true);
		txtGivenname.setEditable(false);
		txtFamilyName.setEditable(false);
		updateButton.setVisible(false);
	}
	
	/*
	 *
	 * @parameters: NULL
	 * @returns NULL
	 * @function update the user profile details in DB
	 */
	public void updateProfile()
	{
		try
		{
			if(!validate())
			{
				lblStatus.setText("All the fields are Mandatory!");
			}
			else if(!txtPassword.getText().equals(txtCnfPassword.getText()))
			{
				lblStatus.setText("Password Mismatch!");
				txtPassword.setText("");
				txtCnfPassword.setText("");
			}
			else
			{
				User userObj = new User();
				userObj.setUsername(LoginController.getLoggedInData().getUsername());
				userObj.setGivenName(txtGivenname.getText());
				userObj.setFamilyName(txtFamilyName.getText());
				userObj.setEmailAddress(txtEmail.getText());
				userObj.setPassword(txtPassword.getText());
				//Updating the user in DB
				if(User.updateUser(userObj))
				{
					lblStatus.setText("Update Successful!");
					//Setting values for the user object globally across the application
					LoginController.setLoggedInData(User.getUserDetails(LoginController.getLoggedInData().getUsername()));
					disable();
					updateButton.setVisible(false);
				}
			}
		}
		catch(Exception e)
		{
			lblStatus.setText("All the fields are Mandatory.");
		}
	}
	
	
	/*
	 *
	 * @parameters: NULL
	 * @returns true if all the mandatory parameters are entered by user else returns false 
	 */
	public boolean validate()
	{
		if(Util.isNullorEmpty(txtGivenname.getText()) || Util.isNullorEmpty(txtFamilyName.getText())
				|| Util.isNullorEmpty(txtEmail.getText()) || Util.isNullorEmpty(txtPassword.getText()) 
				|| Util.isNullorEmpty(txtCnfPassword.getText()))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/*
	 *
	 * @parameters: Action Event Object
	 * @throws Exception
	 * @returns NULL
	 * @function: redirect User back to DashBoard
	 */
	public void redirectToPage(ActionEvent eventObj) throws Exception
	{
		 Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
	}
	
}
