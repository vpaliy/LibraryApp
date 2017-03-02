import com.vpaliy.common.Preconditions;

import org.junit.Before;
import org.junit.Test;

public class PreconditionsTest {

    private String[] data;

    @Before
    public void setData() {
        data=new String[] {"one","two","three","four","five","six"};
    }

    @Test
    public void testCheckIfContainsMethod() {
        String[] sample=new String[]{"one","two","three","four","five","six"};
        Preconditions.checkIfContains(data,sample);
    }

    //I know that's stupid
    @Test(expected = NullPointerException.class)
    public void testCheckNotNullMethod() {
        Preconditions.checkNotNull(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckIfContainsAndThrowIndexException() {
        String[] biggerDataSet=new String[]{"one","two","three","four","five","six","seven"};
        Preconditions.checkIfContains(data,biggerDataSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfContainsAndThrowIllegalArgEx() {
        String[] wrongData=new String[]{"seven","eight","nine","ten","isItElevenNow?","yesItWas"};
        Preconditions.checkIfContains(data,wrongData);
    }

}
