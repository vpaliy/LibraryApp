package com.vpaliy.datasource.source;


import android.content.Context;

import com.vpaliy.data.entity.BookEntity;
import com.vpaliy.data.entity.UserEntity;
import com.vpaliy.data.source.DataSource;
import com.vpaliy.data.source.local.PersistenceContract;
import com.vpaliy.data.source.repository.Repository;
import com.vpaliy.data.specification.SQLSpecification;
import com.vpaliy.data.specification.Specification;
import com.vpaliy.datasource.source.fake.FakeBookSQLSpecification;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.junit.Test;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    private static final String FIRST_NAME="Jack";
    private static final String LAST_NAME="Richer";

    private static final String TITLE="Essentialism";
    private static final String AUTHOR="Greg McKeown";

    private static final int ID=1234;

    @Mock
    private DataSource<UserEntity,Specification> remoteUsersDataSource;

    @Mock
    private DataSource<UserEntity,SQLSpecification> localUsersDataSource;

    @Mock
    private DataSource<BookEntity,Specification> remoteBooksDataSource;

    @Mock
    private DataSource<BookEntity,SQLSpecification> localBooksDataSource;

    @Mock
    private Context context;


    private FakeBookSQLSpecification fakeBookSQLSpecification=new FakeBookSQLSpecification();
    private Repository<UserEntity,SQLSpecification,Specification> usersRepository;

    private Repository<BookEntity,SQLSpecification,Specification> booksRepository;

    @Before
    public void setupRepository() {
        usersRepository=new Repository<>(localUsersDataSource,remoteUsersDataSource);
        booksRepository=new Repository<>(localBooksDataSource,remoteBooksDataSource);
    }

    @Test
    public void testUsersRepositoryAddMethod() {
        final UserEntity userEntity=provideWithUser();
        usersRepository.add(userEntity);

        verify(remoteUsersDataSource).add(userEntity);
        verify(localUsersDataSource).add(userEntity);

    }

    @Test
    public void testBooksRepositoryAddMethod() {
        final BookEntity bookEntity=provideWithBook();
        booksRepository.add(bookEntity);

        verify(remoteBooksDataSource).add(bookEntity);
        verify(localBooksDataSource).add(bookEntity);

    }

    private UserEntity provideWithUser() {
        UserEntity userEntity=new UserEntity(FIRST_NAME,LAST_NAME,ID);
        userEntity.setEmailAddress("vpaliy97@gmail.com");
        userEntity.setAge(20);
        return userEntity;
    }

    private BookEntity provideWithBook() {
        BookEntity bookEntity=new BookEntity(AUTHOR,TITLE,ID);
        bookEntity.setAgeRestriction(0);
        bookEntity.setNumberOfPages(300);
        bookEntity.setDescription("Awesome!");
        bookEntity.setGenre("Self-Development");
        return bookEntity;
    }


}
