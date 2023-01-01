/**
* 
*/
public class RequiredTechnology {

    /**
	* 
	*/
    private Integer requiredTechnologyId;

    /**
	* 
	*/
    private Technology technology;

    /**
	* 
	*/
    private Technology requiredTechnology;

    /**
	* @param requiredTechnologyId
	*/
    public void setRequiredTechnologyId(Integer requiredTechnologyId) {
        this.requiredTechnologyId = requiredTechnologyId;
    }

    /**
	* @return 
	*/
    public Integer getRequiredTechnologyId() {
        return requiredTechnologyId;
    }

    /**
	* @param technology
	*/
    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    /**
	* @return 
	*/
    public Technology getTechnology() {
        return technology;
    }

    /**
	* @param requiredTechnology
	*/
    public void setRequiredTechnology(Technology requiredTechnology) {
        this.requiredTechnology = requiredTechnology;
    }

    /**
	* @return 
	*/
    public Technology getRequiredTechnology() {
        return requiredTechnology;
    }
}
