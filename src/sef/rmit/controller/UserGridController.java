/*
 * @author: 
 */

package sef.rmit.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import sef.rmit.custom.Grid;
import sef.rmit.custom.Simulation;
import sef.rmit.email.Email;
import sef.rmit.email.TemplateData;
import sef.rmit.util.Util;

@SuppressWarnings({"unchecked","rawtypes","deprecation"})
public class UserGridController implements Initializable 
{
	// Value injected by FXMLLoader
	@FXML
	private AnchorPane pane;
	@FXML
	private ImageView button1;
	@FXML
	private ImageView button2;
	@FXML
	private ImageView button3;
	@FXML
	private ImageView button4;
	@FXML
	private ImageView button5;
	@FXML
	private ImageView button6;
	@FXML
	private ImageView button7;
	@FXML
	private ImageView button8;
	@FXML
	private ImageView button9;
	@FXML
	private ImageView button10;
	@FXML
	private ImageView button11;
	@FXML
	private ImageView button12;
	@FXML
	private ImageView button13;
	@FXML
	private ImageView button14;
	@FXML
	private ImageView button15;
	@FXML
	private ImageView button16;
	@FXML
	private ImageView button17;
	@FXML
	private ImageView button18;
	@FXML
	private Rectangle road1;
	@FXML
	private Rectangle road2;
	@FXML
	private Rectangle road3;
	@FXML
	private Rectangle road4;
	@FXML
	private Rectangle road5;
	@FXML
	private Rectangle road6;
	@FXML
	private JFXButton closeButton;
	@FXML
	private JFXButton simulate;

	Image HRed = new Image("images/HRed.png");
	Image HGreen = new Image("images/HGreen.png");
	Image HAmber = new Image("images/HAmber.png");
	Image VRed = new Image("images/VRed.png");
	Image VGreen = new Image("images/VGreen.png");
	Image VAmber = new Image("images/VAmber.png");

	public boolean guiRunning = false;
	private Simulation simulationObj;
	private static ArrayList<ImageView> buttonArray = new ArrayList<ImageView>();
	private static HashMap<Integer, Double>lightTimings = new HashMap<Integer, Double>();
	private static HashMap<Integer,Integer> oldCars = new HashMap<Integer, Integer>();
	private static HashMap<Integer, String> oldLights = new HashMap<Integer, String>();
	private static HashMap<Integer, ArrayList<Integer>> placeIDs = new HashMap<Integer, ArrayList<Integer>>();
	private static HashMap<Integer, ImageView> cars = new HashMap<Integer, ImageView>();
	private static Grid grid;

	public Simulation getSimulationObj() 
	{
		return simulationObj;
	}

	/*
	 * @parameter: Grid Object
	 * @returns NULL
	 * @function: display Grid by loading from fxml and generate cars in UI
	 */
	public static void displayGrid(Grid gridObj) throws Exception 
	{
		if(gridObj != null)
		{
			grid = gridObj;
			ClassLoader classLoader = UserGridController.class.getClassLoader();
			Pane root = FXMLLoader.load(classLoader.getResource("fxml/UserGrid.fxml"));
			AnchorPane paneElem = (AnchorPane) root.getChildren().get(0);
			if(cars.isEmpty())
			{
				int count = 0;
				for(int incre = 1; incre <= 100; incre++)
				{
					String filePath = "images/car1.png";
					if(incre % 5 == 0)
					{
						filePath = "images/car2.png";
					}
					else if(incre % 2 == 0)
					{
						filePath = "images/car1.png";
					}
					else if(incre % 7 == 0)
					{
						filePath = "images/car3.png";
					}
					else if(incre % 3 == 0)
					{
						filePath = "images/car4.png";
					}
					Image image = new Image(filePath);	
					ImageView car = new ImageView();
					car.setFitHeight(34.0);
					car.setFitWidth(44.0);
					car.setLayoutX(-50.0);
					car.setPreserveRatio(true); 
					car.setPickOnBounds(true);
					car.setId("car"+incre);
					car.setImage(image);
					paneElem.getChildren().add(car);
					cars.put(count,car);
					count++;
				}
			}
			Stage primaryStage = Util.getPrimaryStage();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Simulation UI");
			primaryStage.show();
		}
	}
	/*
	 * @parameter: gridID
	 * @returns NULL
	 * @function: generates Grid based on the configuration specified in gridID
	 */ 
	public void createGridByID(String gridID)
	{
		if(gridID.charAt(0) == '0')
		{
			road1.setVisible(false);
			button1.setVisible(false);
			button2.setVisible(false);
			button7.setVisible(false);
			button8.setVisible(false);
			button13.setVisible(false);
			button14.setVisible(false);
		}
		if(gridID.charAt(1) == '0')
		{
			road2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button9.setVisible(false);
			button10.setVisible(false);
			button15.setVisible(false);
			button16.setVisible(false);
		}
		if(gridID.charAt(2) == '0')
		{
			road3.setVisible(false);
			button5.setVisible(false);
			button6.setVisible(false);
			button11.setVisible(false);
			button12.setVisible(false);
			button17.setVisible(false);
			button18.setVisible(false);
		}
		if(gridID.charAt(3) == '0')
		{
			road4.setVisible(false);
			button1.setVisible(false);
			button2.setVisible(false);
			button3.setVisible(false);
			button4.setVisible(false);
			button5.setVisible(false);
			button6.setVisible(false);
		}
		if(gridID.charAt(4) == '0')
		{
			road5.setVisible(false);
			button7.setVisible(false);
			button8.setVisible(false);
			button9.setVisible(false);
			button10.setVisible(false);
			button11.setVisible(false);
			button12.setVisible(false);
		}
		if(gridID.charAt(5) == '0')
		{
			road6.setVisible(false);
			button13.setVisible(false);
			button14.setVisible(false);
			button15.setVisible(false);
			button16.setVisible(false);
			button17.setVisible(false);
			button18.setVisible(false);
		}
	}

	/*
	 * @parameter: NULL 
	 * @returns Dialog
	 * @function: generates Dialog box for light Timing buttons
	 */ 
	public Dialog initializeTimingButton()
	{
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		TextField horizontalTime = new TextField();
		TextField verticalTime = new TextField();
		//Validating user input
		Util.validateInput(horizontalTime, null, 2, false);
		Util.validateInput(verticalTime, null, 2, false);
		ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);

		dialog.setTitle("Traffic Light OnTime");
		dialog.setHeaderText("Set the time duration in sec for the Green traffic light");
		dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		horizontalTime.setPromptText("Horizontal road duration");
		verticalTime.setPromptText("Vertical road duration");

		grid.add(new Label("Horizontal road duration:"), 0, 0);
		grid.add(horizontalTime, 1, 0);
		grid.add(new Label("Vertical road duration:"), 0, 1);
		grid.add(verticalTime, 1, 1);

		Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
		saveButton.setDisable(true);

		verticalTime.textProperty().addListener((observable, oldValue, newValue) -> 
		{
			saveButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		Platform.runLater(() -> horizontalTime.requestFocus());

		dialog.setResultConverter(dialogButton -> 
		{
			if (dialogButton == saveButtonType) 
			{
				return new Pair<>(horizontalTime.getText(), verticalTime.getText());
			}
			return null;
		});
		return dialog;

	}

	/*
	 * @parameter: MouseEvent
	 * @returns NULL
	 * @function: exiting the simulation window
	 */ 
	public void exitUI(MouseEvent event)
	{
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Exit Simulation");
		dialog.setHeaderText("Are you sure?");
		ButtonType yesButton = new ButtonType("Yes");
		ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == yesButton) 
			{
				try 
				{
					Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
					simulationObj.stop();
					clearALLMaps();
				} 
				catch (Exception e) 
				{
					//e.printStackTrace();
				}
			}
			else if (dialogButton == noButton)
			{
				dialog.close();
			}
			else
			{
				dialog.close();
			}
			return null;
		});
		dialog.getDialogPane().getButtonTypes().addAll(yesButton, noButton);
		//dialog.getDialogPane().setContent(grid);
		dialog.show();
	}

	/*
	 * @parameter: MouseEvent
	 * @returns NULL
	 * @function: redirection to Dashboard on click of exit
	 */ 
	public void closeUI(MouseEvent event) throws Exception
	{
		//Redirect to Dashboard based on ROLE after successful login
		Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */ 
	public void setLight1(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(1, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(2, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight3(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(3, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(4, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight5(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(5, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(6, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight7(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(7, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(8, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight9(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(9, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(10, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight11(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(11, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(12, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight13(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(13, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(14, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight15(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(15, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(16, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: MouseEvent 
	 * @returns NULL
	 * @function: setting values for lightTimings based on user input
	 */
	public void setLight17(MouseEvent event)
	{
		Optional<Pair<String, String>> result = initializeTimingButton().showAndWait();
		result.ifPresent(usernamePassword -> 
		{
			lightTimings.put(17, Double.parseDouble(usernamePassword.getKey()));
			lightTimings.put(18, Double.parseDouble(usernamePassword.getValue()));
		});
	}

	/*
	 * @parameter: path to fxml, resources
	 * @returns NULL
	 * @function: initializing objects in the UI after the UI has been loaded
	 */
	@Override 
	// This method is called by the FXMLLoader when initialization is complete
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) 
	{
		/* Code for creating grid*/
		createGridByID(grid.getGridID());

		/*setting the x and y coordinates for each place */
		if(placeIDs.isEmpty())
		{
			placeIDs.put(111, new ArrayList<Integer>(Arrays.asList(160,570)));
			placeIDs.put(112, new ArrayList<Integer>(Arrays.asList(160,500)));
			placeIDs.put(113, new ArrayList<Integer>(Arrays.asList(160,430)));
			placeIDs.put(114, new ArrayList<Integer>(Arrays.asList(160,360)));
			placeIDs.put(115, new ArrayList<Integer>(Arrays.asList(160,300)));
			placeIDs.put(116, new ArrayList<Integer>(Arrays.asList(160,230)));
			placeIDs.put(117, new ArrayList<Integer>(Arrays.asList(160,160)));
			placeIDs.put(118, new ArrayList<Integer>(Arrays.asList(160,100)));
			placeIDs.put(119, new ArrayList<Integer>(Arrays.asList(160,0)));

			placeIDs.put(211, new ArrayList<Integer>(Arrays.asList(412,570)));
			placeIDs.put(212, new ArrayList<Integer>(Arrays.asList(412,500)));
			placeIDs.put(213, new ArrayList<Integer>(Arrays.asList(412,430)));
			placeIDs.put(214, new ArrayList<Integer>(Arrays.asList(412,360)));
			placeIDs.put(215, new ArrayList<Integer>(Arrays.asList(412,300)));
			placeIDs.put(216, new ArrayList<Integer>(Arrays.asList(412,230)));
			placeIDs.put(217, new ArrayList<Integer>(Arrays.asList(412,160)));
			placeIDs.put(218, new ArrayList<Integer>(Arrays.asList(412,100)));
			placeIDs.put(219, new ArrayList<Integer>(Arrays.asList(412,0)));

			placeIDs.put(311, new ArrayList<Integer>(Arrays.asList(664,570)));
			placeIDs.put(312, new ArrayList<Integer>(Arrays.asList(664,500)));
			placeIDs.put(313, new ArrayList<Integer>(Arrays.asList(664,430)));
			placeIDs.put(314, new ArrayList<Integer>(Arrays.asList(664,360)));
			placeIDs.put(315, new ArrayList<Integer>(Arrays.asList(664,300)));
			placeIDs.put(316, new ArrayList<Integer>(Arrays.asList(664,230)));
			placeIDs.put(317, new ArrayList<Integer>(Arrays.asList(664,160)));
			placeIDs.put(318, new ArrayList<Integer>(Arrays.asList(664,100)));
			placeIDs.put(319, new ArrayList<Integer>(Arrays.asList(664,0)));

			placeIDs.put(411, new ArrayList<Integer>(Arrays.asList(115,85)));
			placeIDs.put(412, new ArrayList<Integer>(Arrays.asList(180,85)));
			placeIDs.put(413, new ArrayList<Integer>(Arrays.asList(270,85)));
			placeIDs.put(414, new ArrayList<Integer>(Arrays.asList(360,85)));
			placeIDs.put(415, new ArrayList<Integer>(Arrays.asList(430,85)));
			placeIDs.put(416, new ArrayList<Integer>(Arrays.asList(530,85)));
			placeIDs.put(417, new ArrayList<Integer>(Arrays.asList(620,85)));
			placeIDs.put(418, new ArrayList<Integer>(Arrays.asList(700,85)));
			placeIDs.put(419, new ArrayList<Integer>(Arrays.asList(830,85)));

			placeIDs.put(511, new ArrayList<Integer>(Arrays.asList(115,285)));
			placeIDs.put(512, new ArrayList<Integer>(Arrays.asList(180,285)));
			placeIDs.put(513, new ArrayList<Integer>(Arrays.asList(270,285)));
			placeIDs.put(514, new ArrayList<Integer>(Arrays.asList(360,285)));
			placeIDs.put(515, new ArrayList<Integer>(Arrays.asList(430,285)));
			placeIDs.put(516, new ArrayList<Integer>(Arrays.asList(530,285)));
			placeIDs.put(517, new ArrayList<Integer>(Arrays.asList(620,285)));
			placeIDs.put(518, new ArrayList<Integer>(Arrays.asList(700,285)));
			placeIDs.put(519, new ArrayList<Integer>(Arrays.asList(830,285)));

			placeIDs.put(611, new ArrayList<Integer>(Arrays.asList(115,485)));
			placeIDs.put(612, new ArrayList<Integer>(Arrays.asList(180,485)));
			placeIDs.put(613, new ArrayList<Integer>(Arrays.asList(270,485)));
			placeIDs.put(614, new ArrayList<Integer>(Arrays.asList(360,485)));
			placeIDs.put(615, new ArrayList<Integer>(Arrays.asList(430,485)));
			placeIDs.put(616, new ArrayList<Integer>(Arrays.asList(530,485)));
			placeIDs.put(617, new ArrayList<Integer>(Arrays.asList(620,485)));
			placeIDs.put(618, new ArrayList<Integer>(Arrays.asList(700,485)));
			placeIDs.put(619, new ArrayList<Integer>(Arrays.asList(830,485)));

			placeIDs.put(121, new ArrayList<Integer>(Arrays.asList(195,20)));
			placeIDs.put(122, new ArrayList<Integer>(Arrays.asList(195,120)));
			placeIDs.put(123, new ArrayList<Integer>(Arrays.asList(195,180)));
			placeIDs.put(124, new ArrayList<Integer>(Arrays.asList(195,250)));
			placeIDs.put(125, new ArrayList<Integer>(Arrays.asList(195,320)));
			placeIDs.put(126, new ArrayList<Integer>(Arrays.asList(195,380)));
			placeIDs.put(127, new ArrayList<Integer>(Arrays.asList(195,450)));
			placeIDs.put(128, new ArrayList<Integer>(Arrays.asList(195,520)));
			placeIDs.put(129, new ArrayList<Integer>(Arrays.asList(195,630)));

			placeIDs.put(221, new ArrayList<Integer>(Arrays.asList(447,20)));
			placeIDs.put(222, new ArrayList<Integer>(Arrays.asList(447,120)));
			placeIDs.put(223, new ArrayList<Integer>(Arrays.asList(447,180)));
			placeIDs.put(224, new ArrayList<Integer>(Arrays.asList(447,250)));
			placeIDs.put(225, new ArrayList<Integer>(Arrays.asList(447,320)));
			placeIDs.put(226, new ArrayList<Integer>(Arrays.asList(447,380)));
			placeIDs.put(227, new ArrayList<Integer>(Arrays.asList(447,450)));
			placeIDs.put(228, new ArrayList<Integer>(Arrays.asList(447,520)));
			placeIDs.put(229, new ArrayList<Integer>(Arrays.asList(447,630)));

			placeIDs.put(321, new ArrayList<Integer>(Arrays.asList(699,20)));
			placeIDs.put(322, new ArrayList<Integer>(Arrays.asList(699,120)));
			placeIDs.put(323, new ArrayList<Integer>(Arrays.asList(699,180)));
			placeIDs.put(324, new ArrayList<Integer>(Arrays.asList(699,250)));
			placeIDs.put(325, new ArrayList<Integer>(Arrays.asList(699,320)));
			placeIDs.put(326, new ArrayList<Integer>(Arrays.asList(699,380)));
			placeIDs.put(327, new ArrayList<Integer>(Arrays.asList(699,450)));
			placeIDs.put(328, new ArrayList<Integer>(Arrays.asList(699,520)));
			placeIDs.put(329, new ArrayList<Integer>(Arrays.asList(699,630)));

			placeIDs.put(421, new ArrayList<Integer>(Arrays.asList(750,120)));
			placeIDs.put(422, new ArrayList<Integer>(Arrays.asList(680,120)));
			placeIDs.put(423, new ArrayList<Integer>(Arrays.asList(590,120)));
			placeIDs.put(424, new ArrayList<Integer>(Arrays.asList(500,120)));
			placeIDs.put(425, new ArrayList<Integer>(Arrays.asList(430,120)));
			placeIDs.put(426, new ArrayList<Integer>(Arrays.asList(340,120)));
			placeIDs.put(427, new ArrayList<Integer>(Arrays.asList(250,120)));
			placeIDs.put(428, new ArrayList<Integer>(Arrays.asList(150,120)));
			placeIDs.put(429, new ArrayList<Integer>(Arrays.asList(0,120)));

			placeIDs.put(521, new ArrayList<Integer>(Arrays.asList(750,315)));
			placeIDs.put(522, new ArrayList<Integer>(Arrays.asList(680,315)));
			placeIDs.put(523, new ArrayList<Integer>(Arrays.asList(590,315)));
			placeIDs.put(524, new ArrayList<Integer>(Arrays.asList(500,315)));
			placeIDs.put(525, new ArrayList<Integer>(Arrays.asList(430,315)));
			placeIDs.put(526, new ArrayList<Integer>(Arrays.asList(340,315)));
			placeIDs.put(527, new ArrayList<Integer>(Arrays.asList(250,315)));
			placeIDs.put(528, new ArrayList<Integer>(Arrays.asList(150,315)));
			placeIDs.put(529, new ArrayList<Integer>(Arrays.asList(0,315)));

			placeIDs.put(621, new ArrayList<Integer>(Arrays.asList(750,515)));
			placeIDs.put(622, new ArrayList<Integer>(Arrays.asList(680,515)));
			placeIDs.put(623, new ArrayList<Integer>(Arrays.asList(590,515)));
			placeIDs.put(624, new ArrayList<Integer>(Arrays.asList(500,515)));
			placeIDs.put(625, new ArrayList<Integer>(Arrays.asList(430,515)));
			placeIDs.put(626, new ArrayList<Integer>(Arrays.asList(340,515)));
			placeIDs.put(627, new ArrayList<Integer>(Arrays.asList(250,515)));
			placeIDs.put(628, new ArrayList<Integer>(Arrays.asList(150,515)));
			placeIDs.put(629, new ArrayList<Integer>(Arrays.asList(0,515)));
		}


		if(buttonArray.isEmpty())
		{
			buttonArray.add(button1);
			buttonArray.add(button2);
			buttonArray.add(button3);
			buttonArray.add(button4);
			buttonArray.add(button5);
			buttonArray.add(button6);
			buttonArray.add(button7);
			buttonArray.add(button8);
			buttonArray.add(button9);
			buttonArray.add(button10);
			buttonArray.add(button11);
			buttonArray.add(button12);
			buttonArray.add(button13);
			buttonArray.add(button14);
			buttonArray.add(button15);
			buttonArray.add(button16);
			buttonArray.add(button17);
			buttonArray.add(button18);
		}
	}

	
	/*
	 * @parameter: NULL
	 * @returns NULL
	 * @function: clearing hashmaps at the end
	 */
	public static void clearALLMaps()
	{
		lightTimings = new HashMap();
		oldCars = new HashMap();
		oldLights = new HashMap();
	}	

	
	/*
	 * @parameter: ActionEvent 
	 * @returns NULL
	 * @function: it triggers the Simulation by calling method from Simulation class
	 */
	public void simulateAction(ActionEvent event) throws Exception
	{
		simulationObj = new Simulation(grid, lightTimings);
		simulationObj.runSimulation(UserGridController.this);
		simulationObj.setSimulationStartDate();
		Thread threadObj = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					Email.sendEmailToReciptent(TemplateData.SIMULATION_START_TEMPLATE.getValue(), LoginController.getLoggedInData().getUsername(), "Traffic Simulator Simulation Started", null);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		threadObj.start();
	}

	
	/*
	 * @parameter: currentCars HashMap, currentLights HashMap
	 * @returns NULL
	 * @function: Updating UI based on the current Cars and Lights given by Simulation obj
	 */
	public void simulater(HashMap<Integer,Integer> currentCars, HashMap<Integer, String> currentLights)
	{
		guiRunning = true;
		/*Code to move cars*/
		ParallelTransition parallelTransition = new ParallelTransition();
		PathTransition pathTransitionObj = null;

		/* Moving the car which is at the last place of the road */
		for(Integer i: oldCars.keySet() )
		{			
			if(!currentCars.containsKey(i))
			{
				Path pathObj = new Path();
				int lastPlaceID = (oldCars.get(i)/10)*10+9;
				pathObj.getElements().addAll(
						new MoveTo(placeIDs.get(oldCars.get(i)).get(0), placeIDs.get(oldCars.get(i)).get(1)),
						new LineTo(placeIDs.get(lastPlaceID).get(0), placeIDs.get(lastPlaceID).get(1)),
						new MoveTo(-50, -50),
						new LineTo(-51, -50)
						);
				pathTransitionObj = new PathTransition();
				pathTransitionObj.setDuration(new Duration(1000));
				pathTransitionObj.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
				pathTransitionObj.setInterpolator(Interpolator.LINEAR);
				pathTransitionObj.setPath(pathObj);
				pathTransitionObj.setNode(cars.get(i));
				parallelTransition.getChildren().add(pathTransitionObj);
			}
		}

		/* Moving all the cars in the currentCars hashMap by comparing with their previous locations */
		for(Integer i: currentCars.keySet())
		{	
			Path pathObj = new Path();
			/* If the car existed in the previous updation, then move it to the new place*/
			if(oldCars.containsKey(i))
			{
				if(!oldCars.get(i).equals(currentCars.get(i)))
				{

					pathObj.getElements().addAll(
							new MoveTo(placeIDs.get(oldCars.get(i)).get(0), placeIDs.get(oldCars.get(i)).get(1)),
							new LineTo(placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1))
							);

					pathTransitionObj = new PathTransition();
					pathTransitionObj.setDuration(new Duration(1000));
					pathTransitionObj.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
					pathTransitionObj.setInterpolator(Interpolator.LINEAR);
					pathTransitionObj.setPath(pathObj);
					pathTransitionObj.setNode(cars.get(i));
					parallelTransition.getChildren().add(pathTransitionObj);
				}
			}
			/* if it is a new Car, then move it from the start of the road */
			else
			{
				if((int)currentCars.get(i) < 400 )
				{
					if(((int)currentCars.get(i))%100 ==11 )
					{
						pathObj.getElements().addAll(
								new MoveTo(placeIDs.get(currentCars.get(i)).get(0), 50+placeIDs.get(currentCars.get(i)).get(1)),
								new LineTo(placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)));
					}
					else
					{
						pathObj.getElements().addAll(
								new MoveTo(placeIDs.get(currentCars.get(i)).get(0), -50+placeIDs.get(currentCars.get(i)).get(1)),
								new LineTo(placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)));
					}
				}
				else
				{
					if(((int)currentCars.get(i))%100 ==11 )
					{
						pathObj.getElements().addAll(
								new MoveTo(-50+placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)),
								new LineTo(placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)));
					}
					else
					{
						pathObj.getElements().addAll(
								new MoveTo(50+placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)),
								new LineTo(placeIDs.get(currentCars.get(i)).get(0), placeIDs.get(currentCars.get(i)).get(1)));
					}
				}
				pathTransitionObj = new PathTransition();
				pathTransitionObj.setDuration(new Duration(1000));
				pathTransitionObj.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
				pathTransitionObj.setInterpolator(Interpolator.LINEAR);
				pathTransitionObj.setPath(pathObj);
				pathTransitionObj.setNode(cars.get(i));
				parallelTransition.getChildren().add(pathTransitionObj);
			}
		}
		oldCars.clear();
		for(Integer i: currentCars.keySet())
		{
			oldCars.put(i, currentCars.get(i));
		}

		/* Updating Lights */
		for (Integer iterator: currentLights.keySet())
		{	
			if(oldLights.size() == 0 || !currentLights.get(iterator).equals(oldLights.get(iterator)))
			{
				if(currentLights.get(iterator).equals("Green"))
				{
					if(iterator % 2 != 0)
					{
						buttonArray.get(iterator-1).setImage(HGreen);
					}
					else
					{
						buttonArray.get(iterator-1).setImage(VGreen);
					}
				}
				else if(currentLights.get(iterator).equals("Red"))
				{
					if(iterator % 2 != 0)
					{
						buttonArray.get(iterator-1).setImage(HRed);
					}
					else
					{
						buttonArray.get(iterator-1).setImage(VRed);
					}
				}
				else
				{
					if(iterator % 2 != 0)
					{
						buttonArray.get(iterator-1).setImage(HAmber);
					}
					else
					{
						buttonArray.get(iterator-1).setImage(VAmber);
					}
				}
				if(oldLights.containsKey(iterator))
				{
					oldLights.replace(iterator, currentLights.get(iterator));
				}
				else
				{
					oldLights.put(iterator, currentLights.get(iterator));
				}
			}
		}
		try
		{
			if(parallelTransition != null)
			{
				parallelTransition.play();
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		guiRunning = false;
	}
}