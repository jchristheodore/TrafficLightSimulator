/*
 * @author:
 */

package sef.rmit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sef.rmit.custom.Graph;
import sef.rmit.util.Util;

@SuppressWarnings({"rawtypes","unchecked"})
public class DisplayGraphController
{
	@FXML
	private BarChart<String, Number> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	private static Graph graphObj;
	
	public void initialize()
	{
		//Set the label for X-axis and Y-axis
        xAxis.setLabel("Roads");  
        xAxis.setTickLabelRotation(90);
        //Set label for the graph
        yAxis.setLabel("Number of cars passed");
        //Intialize chart series object
		XYChart.Series side1 = new XYChart.Series();
		side1.setName("Side 1");
        side1.getData().addAll(new XYChart.Data("Road1", graphObj.getRoad1Side1Cars()),
        		new XYChart.Data("Road2", graphObj.getRoad2Side1Cars()),
        		new XYChart.Data("Road3", graphObj.getRoad3Side1Cars()),
        		new XYChart.Data("Road4", graphObj.getRoad4Side1Cars()),
        		new XYChart.Data("Road5", graphObj.getRoad5Side1Cars()),
        		new XYChart.Data("Road6", graphObj.getRoad6Side1Cars()));
        //Add the series to the graph
        barChart.getData().add(side1);
        XYChart.Series side2 = new XYChart.Series();
        side2.setName("Side 2");
        side2.getData().addAll(new XYChart.Data("Road1", graphObj.getRoad1Side2Cars()),
        		new XYChart.Data("Road2", graphObj.getRoad2Side2Cars()),
        		new XYChart.Data("Road3", graphObj.getRoad3Side2Cars()),
        		new XYChart.Data("Road4", graphObj.getRoad4Side2Cars()),
        		new XYChart.Data("Road5", graphObj.getRoad5Side2Cars()),
        		new XYChart.Data("Road6", graphObj.getRoad6Side2Cars()));
     	//Add the series to the graph
        barChart.getData().add(side2);
    }
	
	//Get graphObject
	public Graph getGraph() 
	{
		return graphObj;
	}
	
	//Set the selected graph Object
	public static void setGraph(Graph graph) throws Exception
	{
		/*System.out.println("ROAD11 "+graph.getRoad1Side1Cars());
		System.out.println("ROAD12 "+graph.getRoad1Side2Cars());

		System.out.println("ROAD21 "+graph.getRoad2Side1Cars());

		System.out.println("ROAD22 "+graph.getRoad2Side2Cars());

		System.out.println("ROAD31 "+graph.getRoad3Side1Cars());

		System.out.println("ROAD32 "+graph.getRoad3Side2Cars());
		System.out.println("ROAD41 "+graph.getRoad4Side1Cars());	
		System.out.println("ROAD42 "+graph.getRoad4Side2Cars());

		System.out.println("ROAD51 "+graph.getRoad5Side1Cars());

		System.out.println("ROAD52 "+graph.getRoad5Side2Cars());

		System.out.println("ROAD61 "+graph.getRoad6Side1Cars());

		System.out.println("ROAD62 "+graph.getRoad6Side2Cars());*/
		graphObj = graph;
		Util.displayUX(DisplayGraphController.class, "fxml/DisplayGraph.fxml", null);
	}
	
	/*
	 * 
	 * @parameters: actionevent Object
	 * @throws Exception
	 * @returns NULL
	 * @function: Redirect to Dashboard UI on click on OK Button
	 */
	 
    @FXML
    void redirectToDashBoard(ActionEvent event) throws Exception 
    {
    	Util.displayUX(DashboardController.class, "fxml/Dashboard.fxml", null);
    }
	
}
