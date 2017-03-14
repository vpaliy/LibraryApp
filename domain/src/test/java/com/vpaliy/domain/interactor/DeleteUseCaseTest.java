package com.vpaliy.domain.interactor;


import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUseCaseTest extends UseCaseTest {


    @Test
    public void testUserDeleteMethod() {
        DeleteUseCase<UserModel> deleteUseCase=new DeleteUseCase<>(mockUserRepository);
        deleteUseCase.execute(FAKE_USER);

        verify(mockUserRepository).delete(FAKE_USER);
        verifyNoMoreInteractions(mockUserRepository);

    }


    @Test
    public void testBookDeleteMethod() {
        DeleteUseCase<BookModel> deleteUseCase=new DeleteUseCase<>(mockBookRepository);
        deleteUseCase.execute(FAKE_BOOK);

        verify(mockBookRepository).delete(FAKE_BOOK);
        verifyNoMoreInteractions(mockBookRepository);

    }

    @Test
    public void testClearBookMethod() {
        DeleteUseCase<BookModel> deleteUseCase=new DeleteUseCase<>(mockBookRepository);
        deleteUseCase.clear();

        verify(mockBookRepository).clear();
        verifyNoMoreInteractions(mockBookRepository);
    }

    @Test
    public void testClearUserMethod() {
        DeleteUseCase<UserModel> deleteUseCase=new DeleteUseCase<>(mockUserRepository);
        deleteUseCase.clear();

        verify(mockUserRepository).clear();
        verifyNoMoreInteractions(mockUserRepository);
    }


}
