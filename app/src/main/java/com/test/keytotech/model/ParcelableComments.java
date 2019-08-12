package com.test.keytotech.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ParcelableComments implements Parcelable, Serializable {

    private String name;
    private int postId;
    private int id;
    private String body;
    private String email;

    protected ParcelableComments(Parcel in) {
        name = in.readString();
        postId = in.readInt();
        id = in.readInt();
        body = in.readString();
        email = in.readString();
    }

    public ParcelableComments(CommentsResponse commentsResponse) {
        this.name = commentsResponse.getName();
        this.postId = commentsResponse.getPostId();
        this.id = commentsResponse.getId();
        this.body = commentsResponse.getBody();
        this.email = commentsResponse.getEmail();
    }

    public static final Creator<ParcelableComments> CREATOR = new Creator<ParcelableComments>() {
        @Override
        public ParcelableComments createFromParcel(Parcel in) {
            return new ParcelableComments(in);
        }

        @Override
        public ParcelableComments[] newArray(int size) {
            return new ParcelableComments[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(postId);
        dest.writeInt(id);
        dest.writeString(body);
        dest.writeString(email);
    }
}
