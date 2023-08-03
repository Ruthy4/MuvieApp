
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.muvies.data.remote.dto.Movie
import com.example.muvies.util.BaseDaoTest
import com.example.muvies.util.TestUtil.parseJsonFileToObject
import com.squareup.moshi.Types
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest : BaseDaoTest() {
    @Test
    fun `verify that data insertion is successful and returns correct size`() {
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val movieList = parseJsonFileToObject<List<Movie>>("sample-movies.json", type)

        dao.insertMovie(movieList)

        val expectedResult = 1
        val actualResult = dao.getMovies().size
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `verify that delete is successful`() {
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val movieList = parseJsonFileToObject<List<Movie>>("sample-movies.json", type)

        dao.insertMovie(movieList)
        dao.deleteMovie(1)

        val expectedResult = 0
        val actualResult = dao.getMovies().size
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `verify that movie title matches correct title`() {
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val movieList = parseJsonFileToObject<List<Movie>>("sample-movies.json", type)

        dao.insertMovie(movieList)

        val expectedResult = movieList?.get(0)?.title
        val actualResult = dao.getMovies()[0].title
        Assert.assertEquals(expectedResult, actualResult)
    }
}
