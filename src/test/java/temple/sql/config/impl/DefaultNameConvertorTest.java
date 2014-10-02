package temple.sql.config.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: shenzhang
 * Date: 9/7/14
 * Time: 10:44 PM
 */
@Ignore
public class DefaultNameConvertorTest {
    private DefaultNameConvertor convertor;

    @Before
    public void setUp() throws Exception {
        convertor = new DefaultNameConvertor();
    }

    @Test
    public void shouldTranslatefield2ColumnCorrectly() throws Exception {
        assertThat(convertor.field2Column("").toLowerCase(), is(""));
        assertThat(convertor.field2Column("abc").toLowerCase(), is("abc"));
        assertThat(convertor.field2Column("aBc").toLowerCase(), is("a_bc"));
        assertThat(convertor.field2Column("ABC").toLowerCase(), is("a_b_c"));
        assertThat(convertor.field2Column("abC").toLowerCase(), is("ab_c"));
    }

    @Test
    public void shouldTranslatecolumn2FieldCorrectly() throws Exception {
        assertThat(convertor.column2Field(""), is(""));
        assertThat(convertor.column2Field("a_b"), is("aB"));
        assertThat(convertor.column2Field("A_B"), is("aB"));
        assertThat(convertor.column2Field("ab"), is("ab"));
    }
}
