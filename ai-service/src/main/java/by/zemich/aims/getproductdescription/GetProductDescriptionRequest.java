package by.zemich.aims.getproductdescription;

class GetProductDescriptionRequest {
    private String jsonDestination;
    private String source;

    public GetProductDescriptionRequest(String jsonDestination,
                                        String source) {
        this.jsonDestination = jsonDestination;
        this.source = source;
    }

    public GetProductDescriptionRequest() {
    }

    public String getJsonDestination() {
        return jsonDestination;
    }

    public void setJsonDestination(String jsonDestination) {
        this.jsonDestination = jsonDestination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
