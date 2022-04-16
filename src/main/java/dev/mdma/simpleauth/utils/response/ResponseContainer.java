package dev.mdma.simpleauth.utils.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ResponseContainer {
    @SerializedName("status")
    private ResponseType status;

    @SerializedName("version")
    private String version;
}
