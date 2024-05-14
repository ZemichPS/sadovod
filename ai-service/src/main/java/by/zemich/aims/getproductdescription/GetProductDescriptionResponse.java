package by.zemich.aims.getproductdescription;

class GetProductDescriptionResponse {
    private String jsonDescription;

    GetProductDescriptionResponse(String jsonDescription) {
        this.jsonDescription = jsonDescription;
    }

    public String getJsonDescription() {
        return jsonDescription;
    }

    public void setJsonDescription(String jsonDescription) {
        this.jsonDescription = jsonDescription;
    }


}
