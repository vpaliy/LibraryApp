package com.vpaliy.mvp.view.wrapper;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 *
 */
public class InputWrapper implements Parcelable {

    private int inputType;
    private int color;
    private int textColor;
    private boolean isPassword;
    private String property;
    private int propertyCode;

    public InputWrapper(@NonNull String property, int propertyCode) {
        this.property=property;
        this.propertyCode=propertyCode;
    }

    public InputWrapper(Parcel parcel) {
        this.inputType=parcel.readInt();
        this.color=parcel.readInt();
        this.textColor=parcel.readInt();
        this.isPassword=parcel.readInt()==1;
        this.property=parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(inputType);
        dest.writeInt(color);
        dest.writeInt(textColor);
        dest.writeInt(isPassword?1:0);
        dest.writeString(property);
    }

    public int getInputType() {
        return inputType;
    }

    public int getColor() {
        return color;
    }

    public int getTextColor() {
        return textColor;
    }

    public boolean isPassword() {
        return isPassword;
    }

    public String getProperty() {
        return property;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPropertyCode() {
        return propertyCode;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public void setIfPassword(boolean password) {
        isPassword = password;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }


    public static Parcelable.Creator<InputWrapper> CREATOR=new Creator<InputWrapper>() {
        @Override
        public InputWrapper createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public InputWrapper[] newArray(int size) {
            return new InputWrapper[0];
        }
    };
}
