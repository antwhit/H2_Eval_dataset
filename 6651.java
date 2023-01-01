class HTTPErrorResource extends HTTPFileResource {

    HTTPErrorResource(int status) throws HTTPErrorException {
        super(HTTPErrorPage.getPath(status));
    }
}
