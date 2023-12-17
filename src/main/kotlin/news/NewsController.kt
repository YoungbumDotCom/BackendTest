package emiyaj.news

import com.beust.klaxon.Klaxon
import emiyaj.common.CommonRequest
import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.news.model.News
import emiyaj.news.model.NewsResponse
import emiyaj.util.SanitizeHTML.sanitizeHTML
import org.springframework.web.bind.annotation.*

/**
 * This class handles news-related HTTP requests.
 * It is annotated with @RestController, indicating that it is a Spring MVC controller that returns data directly in the response body.
 * It is also annotated with @RequestMapping at the class level, specifying that all methods in this class handle requests to the "/news" path.
 */
@RestController
@RequestMapping(value = ["news"])
class NewsController {

    /**
     * This function handles GET requests to the "/get" path.
     * It retrieves the first 5 news items from the news collection and returns them in a NewsResponse object.
     *
     * @return A NewsResponse object containing the retrieved news items.
     */
    @GetMapping(value = ["/get"])
    fun getDefault() = NewsResponse(NewsDatabase.getNews(5, 0))

    /**
     * This function handles GET requests to the "/get/{page}" path.
     * It retrieves a page of news items from the news collection and returns them in a NewsResponse object.
     * Each page contains 10 news items.
     *
     * @param page The page number. Default is 1.
     * @return A NewsResponse object containing the retrieved news items.
     */
    @GetMapping(value = ["/get/{page}"])
    fun get(@PathVariable page: Int = 1) = NewsResponse(NewsDatabase.getNews(10, (page - 1) * 10))

    /**
     * This function handles POST requests to the "/post" path.
     * It inserts a news item into the news collection.
     * The news item is received in the request body and is decrypted before insertion.
     * The function is currently commented out.
     *
     * @param body The request body, represented by a CommonRequest object.
     * @return A CommonResult object representing the result of the insertion.
     */
    /*@PostMapping(value = ["/post"])
    fun post(@RequestBody body: CommonRequest): CommonResult {
        val req = Klaxon().parse<News>(body.decryptedData) ?: return CommonResult(CommonStatus.NOT_OPERATIONAL, "bad request")
        NewsDatabase.postNews(req)
        return CommonResult(CommonStatus.NORMAL, "success")
    }
    */
}