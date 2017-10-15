/*
 * @author:
 */

package sef.rmit.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.io.IOUtils;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sef.rmit.custom.Grid;

@SuppressWarnings("rawtypes")
public class Util
{
	public static final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	private static Stage primaryStage = new Stage();
	/*
	* To check if a string is null or Empty
	*/
	public static Boolean isNullorEmpty(String value)
    {
        if (value != null && !"".equals(value))
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
	 * @parameters: Classname, FxmlFile Path, CssFile Path, EventObj
	 * @throws Exception
	 * @returns StageObject
	 * @displays FXML Document via Stage Object
	 */
	public static Stage displayUX(Class classname, String fxmlFile, String cssFile) throws Exception
	{
		try
		{
			ClassLoader classLoader = classname.getClassLoader();
			Parent root = FXMLLoader.load(classLoader.getResource(fxmlFile));
			Scene scene = new Scene(root);
			if(!Util.isNullorEmpty(cssFile))
			{
				scene.getStylesheets().add(classLoader.getResource(cssFile).toExternalForm());
			}
			primaryStage.setScene(scene);
			primaryStage.show();
			return primaryStage;
		}
		catch(Exception exp)
		{
			throw new Exception(exp);
		}
	}
	
	/*
	 * 
	 * @parameters: GridObj, Event Object
	 * @throws Exception
	 * @returns StageObject
	 * @displays FXML Document via Stage Object
	 */
	public static Stage displayUXFromDB(Grid gridObj) throws Exception
	{
		try
		{	
			FXMLLoader loader = new FXMLLoader();
			InputStream in = IOUtils.toInputStream(gridObj.getgridXML(), "UTF-8");
			Pane root = (Pane) loader.load(in);
			in.close();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			return primaryStage;
		}
		catch(Exception exp)
		{
			throw new Exception(exp);
		}
	}
	
	/*
	 * 
	 * @parametes: NULL
	 * @return current Stage Object
	 * 
	 */
	public static Stage getPrimaryStage() 
	{
	   return primaryStage;
	}
	
	/*
	 * 
	 * @parameters: TextField, length
	 * @returns NULL
	 * @function: Validate input string as numeric and limit the length of the string
	 */
	public static void validateInput(TextField textfield,JFXTextField jfxTextField, int length, boolean enableDecimal)
	{
		String regExp;
		if(enableDecimal)
		{
			regExp = "\\d*.";
		}
		else
		{
			regExp = "\\d*";
		}
		if(textfield != null)
		{
			textfield.textProperty().addListener(new ChangeListener<String>() 
			{
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
		        {
		        	try
		        	{
		                if (!newValue.matches(regExp)) 
		                {
		                	textfield.setText(newValue.replaceAll("[^\\d.]", ""));
		                }
		                else if(newValue.length() > length)
		                {
		                	newValue = newValue.substring(0, newValue.length()-1);
		                	textfield.setText(newValue);
		                }
		        	}
		        	catch(Exception exp)
		        	{
		        		newValue = "";
		        		textfield.setText(newValue);
		        	}
		        }
			});
		}
		else if (jfxTextField != null)
		{
			jfxTextField.textProperty().addListener(new ChangeListener<String>() 
			{
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
		        {
		        	try
		        	{
		                if (!newValue.matches(regExp)) 
		                {
		                	jfxTextField.setText(newValue.replaceAll("[^\\d.]", ""));
		                }
		                else if(newValue.length() > length)
		                {
		                	newValue = newValue.substring(0, newValue.length()-1);
		                	jfxTextField.setText(newValue);
		                }
		        	}
		        	catch(Exception exp)
		        	{
		        		newValue = "";
		        		jfxTextField.setText(newValue);
		        	}
		        }
			});
		}
	}
	
}