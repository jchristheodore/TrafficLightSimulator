package sef.rmit.email;

public enum TemplateData 
{
	REGISTER_TEMPLATE("EMAIL_REGISTER"),	
	SIMULATION_COMPLETE_TEMPLATE("EMAIL_SIMULATION_COMPLETE"),
	SIMULATION_START_TEMPLATE("EMAIL_SIMULATION_START");
	
	private String value;

	TemplateData(final String value) 
	{
		this.value = value; 
	}
	
    public String getValue() 
    {
    	return value; 
    }
}
