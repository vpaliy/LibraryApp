package com.vpaliy.datasource.cache;

import com.vpaliy.data.cache.Serializer;
import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
public class SerializerTest {

    private static final String JSON_RESPONSE_BOOK = "{\n"
            + "    \"id\": 0,\n"
            + "    \"title\": \"Essentialism\",\n"
            + "    \"author\": \"Greg McKeown\",\n"
            + "    \"description\": \"How to eliminate non-essential things\",\n"
            + "    \"genre\":\"Self-Development\",\n"
            + "    \"numberOfPages\":300, \n"
            + "    \"ageRestriction\":2 \n"
            + "}";

    private static final String JSON_RESPONSE_USER = "{\n"
            + "    \"id\": 0,\n"
            + "    \"firstName\": \"Greg\",\n"
            + "    \"lastName\": \"McKeown\",\n"
            + "    \"emailAddress\": \"vpaliy97@gmail.com\",\n"
            + "    \"age\":30 \n"
            + "}";


    private Serializer<BookEntity> bookEntitySerializer;
    private Serializer<UserEntity> userEntitySerializer;

    @Before
    public void setUp() {
        bookEntitySerializer=new Serializer<>();
        userEntitySerializer=new Serializer<>();
    }

    @Test
    public void testSerializeBookEntityCase() {
        BookEntity bookEntity=bookEntitySerializer.deserialize(JSON_RESPONSE_BOOK,BookEntity.class);
        String Json=bookEntitySerializer.serialize(bookEntity,BookEntity.class);
        BookEntity bookEntityExample=bookEntitySerializer.deserialize(Json,BookEntity.class);

        //and now testing
        assertThat(bookEntity.getID(),is(bookEntityExample.getID()));
        assertThat(bookEntity.getAgeRestriction(),is(bookEntityExample.getAgeRestriction()));
        assertThat(bookEntity.getAuthor(),is(bookEntityExample.getAuthor()));
        assertThat(bookEntity.getDescription(),is(bookEntityExample.getDescription()));
        assertThat(bookEntity.getGenre(),is(bookEntityExample.getGenre()));
        assertThat(bookEntity.getNumberOfPages(),is(bookEntityExample.getNumberOfPages()));
        assertThat(bookEntity.getTitle(),is(bookEntityExample.getTitle()));

    }

    @Test
    public void testSerializeUserEntityCase() {
        UserEntity userEntity=userEntitySerializer.deserialize(JSON_RESPONSE_USER,UserEntity.class);
        String JSon=userEntitySerializer.serialize(userEntity,UserEntity.class);
        UserEntity userEntityExample=userEntitySerializer.deserialize(JSon,UserEntity.class);

        //and now testing
        assertThat(userEntity.getID(),is(userEntityExample.getID()));
        assertThat(userEntity.getAge(),is(userEntityExample.getAge()));
        assertThat(userEntity.getEmailAddress(),is(userEntityExample.getEmailAddress()));
        assertThat(userEntity.getFirstName(),is(userEntityExample.getFirstName()));
        assertThat(userEntity.getLastName(),is(userEntityExample.getLastName()));
    }

    @Test
    public void testDeserializeUserEntityCase() {
        UserEntity userEntity=userEntitySerializer.deserialize(JSON_RESPONSE_USER,UserEntity.class);

        assertThat(userEntity.getID(),is(0));
        assertThat(userEntity.getEmailAddress(),is(equalTo("vpaliy97@gmail.com")));
        assertThat(userEntity.getFirstName(),is("Greg"));
        assertThat(userEntity.getLastName(),is("McKeown"));
        //sorry Greg, but I don't know how old are you
        assertThat(userEntity.getAge(),is(30));
    }

    @Test
    public void testDeserializeBookEntityCase() {
        BookEntity bookEntity=bookEntitySerializer.deserialize(JSON_RESPONSE_BOOK,BookEntity.class);

        assertThat(bookEntity.getID(),is(0));
        assertThat(bookEntity.getTitle(),is("Essentialism"));
        assertThat(bookEntity.getAuthor(),is("Greg McKeown"));
        assertThat(bookEntity.getNumberOfPages(),is(300));
        assertThat(bookEntity.getGenre(),is("Self-Development"));
        assertThat(bookEntity.getAgeRestriction(),is(2));
        assertThat(bookEntity.getDescription(),is(equalTo("How to eliminate non-essential things")));
    }

}
