
package com.newpwr.workflow.core.protocol;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public abstract class AbstractIdentifyResponse extends AbstractResultMessage {

    private String version = "1.0.0";

    private String extraData;

    private boolean identified;

    /**
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets extra data.
     *
     * @return the extra data
     */
    public String getExtraData() {
        return extraData;
    }

    /**
     * Sets extra data.
     *
     * @param extraData the extra data
     */
    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    /**
     * Is identified boolean.
     *
     * @return the boolean
     */
    public boolean isIdentified() {
        return identified;
    }

    /**
     * Sets identified.
     *
     * @param identified the identified
     */
    public void setIdentified(boolean identified) {
        this.identified = identified;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("version=");
        result.append(version);
        result.append(",");
        result.append("extraData=");
        result.append(extraData);
        result.append(",");
        result.append("identified=");
        result.append(identified);
        result.append(",");
        result.append("resultCode=");
        result.append(getResultCode());
        result.append(",");
        result.append("msg=");
        result.append(getMsg());

        return result.toString();
    }
}
