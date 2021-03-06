package app.social.network;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNull;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SocialNetworkTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void phase1_createUserWithUsernameDavid() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                                     .content(createUserJson("david", "MALE")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase1_createUserWithUsernameDavid_ConflictEror() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                                     .content(createUserJson("david", "male")))
               .andExpect(status().isConflict());
    }

    @Test
    public void phase1_createUserWithUsernamePhil() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                                     .content(createUserJson("phil", "male")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase1_createUserWithUsernameMargaret() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                                     .content(createUserJson("margaret", "female")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase1_createUserWithUsernameCousinItt() throws Exception {
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
                                     .content(createUserJson("cousin_itt", "other")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase2_getAllUsersOrderedByName() throws Exception {
        mockMvc.perform(get("/user"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(4)))
               .andExpect(jsonPath("$[0].id").isNumber())
               .andExpect(jsonPath("$[0].name", is("cousin_itt")))
               .andExpect(jsonPath("$[0].sex", is("other")))
               .andExpect(jsonPath("$[1].id").isNumber())
               .andExpect(jsonPath("$[1].name", is("david")))
               .andExpect(jsonPath("$[1].sex", is("male")))
               .andExpect(jsonPath("$[2].id").isNumber())
               .andExpect(jsonPath("$[2].name", is("margaret")))
               .andExpect(jsonPath("$[2].sex", is("female")))
               .andExpect(jsonPath("$[3].id").isNumber())
               .andExpect(jsonPath("$[3].name", is("phil")))
               .andExpect(jsonPath("$[3].sex", is("male")));
    }

    @Test
    public void phase2_getMaleUsersOrderedByName() throws Exception {
        mockMvc.perform(get("/user?sex=male"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id").isNumber())
               .andExpect(jsonPath("$[0].name", is("david")))
               .andExpect(jsonPath("$[0].sex", is("male")))
               .andExpect(jsonPath("$[1].id").isNumber())
               .andExpect(jsonPath("$[1].name", is("phil")))
               .andExpect(jsonPath("$[1].sex", is("male")));
    }

    @Test
    public void phase2_getUserWithUsernameDavid() throws Exception {
        mockMvc.perform(get("/user/david"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").isNumber())
               .andExpect(jsonPath("$.name", is("david")))
               .andExpect(jsonPath("$.sex", is("male")));
    }

    @Test
    public void phase2_getUserWithUsernameCousinItt() throws Exception {
        mockMvc.perform(get("/user/cousin_itt"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").isNumber())
               .andExpect(jsonPath("$.name", is("cousin_itt")))
               .andExpect(jsonPath("$.sex", is("other")));
    }

    @Test
    public void phase2_getUserWithUsernameUnknown_NotExists() throws Exception {
        mockMvc.perform(get("/user/unknown"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void phase2_createPost() throws Exception {
        mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON)
                                     .content(createPostJson("david", "This is a test post. Do not reply.")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase2_createPostWithoutComments() throws Exception {
        mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON)
                                     .content(createPostJson("cousin_itt", "Hello!")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase2_createPostParentComment() throws Exception {
        mockMvc.perform(post("/post/1/comment").contentType(MediaType.APPLICATION_JSON)
                                               .content(createCommentJson("phil", "First comment.")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase3_createChildComment1() throws Exception {
        mockMvc.perform(post("/post/1/comment/1").contentType(MediaType.APPLICATION_JSON)
                                                 .content(createCommentJson("margaret", "He asked not to reply...")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase3_createChildComment2() throws Exception {
        mockMvc.perform(post("/post/1/comment/1").contentType(MediaType.APPLICATION_JSON)
                                                 .content(createCommentJson("cousin_itt", "Margaret is right!")))
               .andExpect(status().isCreated());
    }

    @Test
    public void phase3_incrementScoreOfPostWithId1() throws Exception {
        mockMvc.perform(patch("/post/1/score").contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"change\":1}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").isNumber())
               .andExpect(jsonPath("$.user_ref", is("/user/david")))
               .andExpect(jsonPath("$.text", is("This is a test post. Do not reply.")))
               .andExpect(jsonPath("$.score", is(1)))
               .andExpect(jsonPath("$.timestamp").isNumber());
    }

    @Test
    public void phase3_decrementScoreOfPostWithId2() throws Exception {
        mockMvc.perform(patch("/post/2/score").contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"change\":-1}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").isNumber())
               .andExpect(jsonPath("$.user_ref", is("/user/cousin_itt")))
               .andExpect(jsonPath("$.text", is("Hello!")))
               .andExpect(jsonPath("$.score", is(-1)))
               .andExpect(jsonPath("$.timestamp").isNumber());
    }

    @Test
    public void phase3_decrementScoreOfCommentForPost1WithId1() throws Exception {
        mockMvc.perform(patch("/post/1/comment/1/score").contentType(MediaType.APPLICATION_JSON)
                                                        .content("{\"change\":-1}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").isNumber())
               .andExpect(jsonPath("$.user_ref", is("/user/phil")))
               .andExpect(jsonPath("$.text", is("First comment.")))
               .andExpect(jsonPath("$.parent", IsNull.nullValue()))
               .andExpect(jsonPath("$.score", is(-1)))
               .andExpect(jsonPath("$.timestamp").isNumber());
    }

    @Test
    public void phase4_getPostsOrderedByTimestampDescending() throws Exception {
        mockMvc.perform(get("/post"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id", is(2)))
               .andExpect(jsonPath("$[0].user_ref", is("/user/cousin_itt")))
               .andExpect(jsonPath("$[0].comment_ref", is("/post/2/comment")))
               .andExpect(jsonPath("$[0].text", is("Hello!")))
               .andExpect(jsonPath("$[0].score", is(-1)))
               .andExpect(jsonPath("$[0].timestamp").isNumber())
               .andExpect(jsonPath("$[1].id", is(1)))
               .andExpect(jsonPath("$[1].user_ref", is("/user/david")))
               .andExpect(jsonPath("$[1].text", is("This is a test post. Do not reply.")))
               .andExpect(jsonPath("$[1].comment_ref", is("/post/1/comment")))
               .andExpect(jsonPath("$[1].score", is(1)))
               .andExpect(jsonPath("$[1].timestamp").isNumber());
    }

    @Test
    public void phase4_getPostsWithId2() throws Exception {
        mockMvc.perform(get("/post/2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(2)))
               .andExpect(jsonPath("$.user_ref", is("/user/cousin_itt")))
               .andExpect(jsonPath("$.comment_ref", is("/post/2/comment")))
               .andExpect(jsonPath("$.text", is("Hello!")))
               .andExpect(jsonPath("$.score", is(-1)))
               .andExpect(jsonPath("$.timestamp").isNumber());
    }

    @Test
    public void phase4_getCommentsForPost1OrderedByTimestampAscending() throws Exception {
        mockMvc.perform(get("/post/1/comment"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].id").isNumber())
               .andExpect(jsonPath("$[0].user_ref", is("/user/phil")))
               .andExpect(jsonPath("$[0].text", is("First comment.")))
               .andExpect(jsonPath("$[0].parent", IsNull.nullValue()))
               .andExpect(jsonPath("$[0].score", is(-1)))
               .andExpect(jsonPath("$[0].timestamp").isNumber())
               .andExpect(jsonPath("$[1].id").isNumber())
               .andExpect(jsonPath("$[1].user_ref", is("/user/margaret")))
               .andExpect(jsonPath("$[1].text", is("He asked not to reply...")))
               .andExpect(jsonPath("$[1].parent", is(1)))
               .andExpect(jsonPath("$[1].score", is(0)))
               .andExpect(jsonPath("$[1].timestamp").isNumber())
               .andExpect(jsonPath("$[2].id").isNumber())
               .andExpect(jsonPath("$[2].user_ref", is("/user/cousin_itt")))
               .andExpect(jsonPath("$[2].text", is("Margaret is right!")))
               .andExpect(jsonPath("$[2].parent", is(1)))
               .andExpect(jsonPath("$[2].score", is(0)))
               .andExpect(jsonPath("$[2].timestamp").isNumber());
    }

    @Test
    public void phase5_deleteUserWithUsernamePhil() throws Exception {
        mockMvc.perform(delete("/user/phil"))
               .andExpect(status().isOk());
    }

    @Test
    public void phase6_getAllUsersOrderedByName() throws Exception {
        mockMvc.perform(get("/user"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].id").isNumber())
               .andExpect(jsonPath("$[0].name", is("cousin_itt")))
               .andExpect(jsonPath("$[0].sex", is("other")))
               .andExpect(jsonPath("$[1].id").isNumber())
               .andExpect(jsonPath("$[1].name", is("david")))
               .andExpect(jsonPath("$[1].sex", is("male")))
               .andExpect(jsonPath("$[2].id").isNumber())
               .andExpect(jsonPath("$[2].name", is("margaret")))
               .andExpect(jsonPath("$[2].sex", is("female")));
    }

    private String createUserJson(String name, String sex) {
        return String.format("{\"name\":\"%s\",\"sex\":\"%s\"}", name, sex);
    }

    private String createPostJson(String author, String text) {
        return String.format("{\"user\":\"%s\",\"text\":\"%s\"}", author, text);
    }

    private String createCommentJson(String author, String text) {
        return String.format("{\"user\":\"%s\",\"text\":\"%s\"}", author, text);
    }
}
