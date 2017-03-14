package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class AddUseCaseTest extends UseCaseTest{

    @Rule
    public ExpectedException expectedException=ExpectedException.none();

    @Test
    public void testAddBookModel() {
        AddUseCase<BookModel> addBookUseCase=new AddUseCase<>(mockBookRepository);
        addBookUseCase.execute(FAKE_BOOK);

        verify(mockBookRepository).add(FAKE_BOOK);
        verifyNoMoreInteractions(mockBookRepository);
    }

    @Test
    public void testAddUserModel() {
        AddUseCase<UserModel> addUserUserCase=new AddUseCase<>(mockUserRepository);
        addUserUserCase.execute(FAKE_USER);

        verify(mockUserRepository).add(FAKE_USER);
        verifyNoMoreInteractions(mockUserRepository);
    }
}
