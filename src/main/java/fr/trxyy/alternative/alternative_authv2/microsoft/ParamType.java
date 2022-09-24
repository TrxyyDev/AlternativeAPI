package fr.trxyy.alternative.alternative_authv2.microsoft;

public enum ParamType
{
    AUTH("Authentication"),
    REFRESH("Refresh"),
    XBL("XboxLive"),
    XSTS("XSts"),
    MC("Minecraft");

    private final String contentType;

    ParamType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getContentType()
    {
        return this.contentType;
    }
}