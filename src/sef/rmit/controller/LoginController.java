/*
 * @author:
 */

package sef.rmit.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sef.rmit.custom.*;
import sef.rmit.util.*;

@SuppressWarnings({"unused", "deprecation"})
public class LoginController extends Application implements Initializable
{
	
	@FXML
	private JFXTextField txtUsername;
	
	@FXML
	private JFXPasswordField txtPassword;

    @FXML
    private Label lblHeader;
	
	@FXML
	private Label lblStatus;
	
	public boolean userLoggedIn = false;
	
	private static User userObj = new User();
	
	@FXML
    private Label lblHeading;

    @FXML
    private JFXTextField inpFamilyName;

    @FXML
    private JFXPasswordField inpConfirmPasword;
    
    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXPasswordField inpPassword;

    @FXML
    private JFXTextField inpEmail;

    @FXML
    private JFXTextField inpGivenName;

    @FXML
    private JFXProgressBar progressBar;
    
    @FXML
    private JFXSpinner loadingSpinner;

    @FXML
    private AnchorPane mainPane;
    
    private static boolean isDBConnect = false;
    
    private Thread threadObj;
        
	//To Check if User is Logged In
	public boolean isUserLoggedIn()
	{
		return userLoggedIn;
	}
	
	//To Check if User is Logged In
	public static User getLoggedInData()
	{
		return userObj;
	}
	
	//To Check if User is Logged In
	public static void setLoggedInData(User userobj)
	{
		userObj = userobj;
	}
	
	//To CHeck if DB Connection is successful
	public static boolean isDBConnectSuccessful()
	{
		return isDBConnect;
	}
	
	public static void main(String[] args) throws Exception 
	{
		launch(args);
	}
	
	//Intialize the Login UI
	@Override
	public void start(Stage primaryStage) throws Exception 
	{		
		Util.displayUX(LoginController.class, "fxml/Login.fxml", null);
	}
		
	/*
	 * 
	 * @param: ActionEvent
	 * @throws Exception
	 * @function: Logins the user in
	 */
	public void loginUser(ActionEvent event) throws Exception
	{
		try 
		{
			//Check if Username and password is not empty
			if(!Util.isNullorEmpty(txtUsername.getText()) && !Util.isNullorEmpty(txtPassword.getText()))
			{
				//Validate if username and password is correct from DB
				if (User.validateUser(txtUsername.getText(), txtPassword.getText())) 
				{
					//Set the DB Connection status as true
					isDBConnect = true;
					//Store all the user details in Object
					userObj = User.getUserDetails(txtUsername.getText());
					lblStatus.setText("Welcome "+userObj.getGivenName());
					userLoggedIn = true;
					if(userLoggedIn)
					{	
						//Redirect to Dashboard based on ROLE after successful login
						Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
					}
				} 
				else
				{
					lblStatus.setText("Invalid Username or Password!");
				}
			}
			else 
			{
				lblStatus.setText("Username or Password cannot be empty!");
			}
		} 
		catch (SQLException sqlExp) 
		{
			isDBConnect = false;
			lblStatus.setText("Connection Failed. Please, try again later!");
		}
		catch(IndexOutOfBoundsException arrExp)
		{
			lblStatus.setText("Invalid Username or Password!");
		}
	}
	
	/*
	 * @
	 * @param: EventObject
	 * @function: Registers a new user
	 * @throws Exception
	 */
	public void registerUser(ActionEvent event) throws Exception 
	{
		Util.displayUX(LoginController.class, "fxml/Register.fxml", null);	
		//Validate DB Connection
		establishDBConnection();
	}
	
	/*
	 * 
	 * @param: EventObject
	 * @function Insert Object in the User Object
	 * @throws Exception
	 */
	public void submitUser(ActionEvent event) 
	{
		try
		{
			if(!isDBConnect)
			{
				lblStatus.setText("Connection Failed. Please, try again later!");
				//Validate DB Connection
				establishDBConnection();
			}
			else if(Util.isNullorEmpty(inpGivenName.getText()) || Util.isNullorEmpty(inpFamilyName.getText())
					||Util.isNullorEmpty(inpPassword.getText()) || Util.isNullorEmpty(inpConfirmPasword.getText()) 
					|| Util.isNullorEmpty(inpEmail.getText()))
			{
				lblStatus.setText("All Fields are mandatory!");
			}
			else if(!(inpPassword.getText().length() > 5))
			{
				lblStatus.setText("Password should be atleast 6 letters.");
			}
			else if(!inpPassword.getText().equals(inpConfirmPasword.getText()))
			{
				lblStatus.setText("Password mismatch!");
			}
			else if(!emailValidation())
			{
				
			}
			else 
			{
				lblStatus.setText("");
				progressBar.setVisible(true);
				submitButton.setVisible(false);
				cancelButton.setVisible(false);
				//Form the user object data
				User userObj = new User();
				userObj.setUsername(inpEmail.getText());
				userObj.setGivenName(inpGivenName.getText());
				userObj.setFamilyName(inpFamilyName.getText());
				userObj.setEmailAddress(inpEmail.getText());
				userObj.setPassword(inpPassword.getText());
				userObj.setCreatedUser("ADMIN");
				userObj.setLastModifiedUser("ADMIN");
				userObj.setUserRole("USER");
				threadObj = new Thread(new Runnable() 
				{
		            @Override
		            public void run() 
		            {
		                try 
		                {
		                    Thread.sleep(2000l);	
		                    //Insert the user in DB
		    				if(User.insertUser(userObj))
		    				{
		    					//on Successful insertion of data in the DB
		    				}
		                } 
		                catch (Exception e) 
		                {
		        			lblStatus.setText("System Failed. Please, try again later!");
		        			progressBar.setVisible(false);
		        			submitButton.setVisible(true);
		        			cancelButton.setVisible(true);
		                }	
		            }
		        });
				threadObj.start();
				//Redirect to Login UI after successful registration
				Platform.runLater(new Runnable() 
				{
					    @Override
					    public void run() 
					    {
					    	try 
					    	{
								Util.displayUX(LoginController.class, "fxml/Login.fxml", null);
					    	} 
					    	catch (Exception e) 
					    	{
					    		lblStatus.setText("System Failed. Please, try again later!");
					    		threadObj.stop();
					    	}
					    }
				});
			}
		}
		catch(Exception exp)
		{
			lblStatus.setText("Connection Failed. Please, try again later!");
			threadObj.stop();
		}
	}
	
	/*
	 * 
	 * @function: Validates Email on KeyPress eventuserObj
	 * @param: EventObject
	 */
	public void validateEmail(KeyEvent event)
	{
		emailValidation();
	}
	
	/*
	 * 
	 * @function: Validates Email on KeyPress eventuserObj
	 * @param: EventObject
	 */
	public void validateEmailOverMouse(MouseEvent event)
	{
		emailValidation();
	}
	
	/*
	 * 
	 * @function: Checks if DB Connection is Established
	 */
	private void establishDBConnection()
	{
		threadObj = new Thread(new Runnable() 
		{
            @Override
            public void run() 
            {
                try 
                {
					if(User.isDBConnectionEstablished())
					{
						//set isDBConnect true if the DB Connection is established successfully
						isDBConnect = true;	
					}
                }
                catch(Exception exp)
                {
                	lblStatus.setText("Connection Failed. Please, try again later!");
                	threadObj.stop();
                }
            }
		});
		threadObj.start();
	}
	
	/*
	 * 
	 * @function: Validates Email Address
	 */
	private boolean emailValidation()
	{
		try
		{
			//Check if EmailAddress input is not empty
			if(!Util.isNullorEmpty(inpEmail.getText()))
			{
				//Validate the email address using pattern matching
				Pattern patternObj = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
				Matcher matcherObj = patternObj.matcher(inpEmail.getText());
				if (matcherObj.matches())
				{
					lblStatus.setText("");
					//Get the user Details
					User userObj = User.getUserDetails(inpEmail.getText());
					if(userObj != null)
					{
						lblStatus.setText("Email Address already exists!");
						submitButton.setDisable(true);
					}
					else
					{
						submitButton.setDisable(false);
					}
					return true;
				}
				else
				{
					lblStatus.setText("Please use a valid email address");
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch(Exception exp)
		{
			lblStatus.setText("System Failed. Try again later!");
			return false;
		}
	}
	
	/*
	 * 
	 * @param EventObject
	 * @throws Exception
	 * @function: Redirects to Login Page
	 */
	public void cancelForm(ActionEvent event) throws Exception
	{
		Util.displayUX(LoginController.class, "fxml/Login.fxml", null);
	}

	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 * function: Used to set the userObject as Empty after logout and relogin
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		//
	}
	
}
