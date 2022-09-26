package com.example.publicwifiinfo2022.apidto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BaseDto {

    @SerializedName("TbPublicWifiInfo")
    private MainDto tbPublicWifiInfo;

}
