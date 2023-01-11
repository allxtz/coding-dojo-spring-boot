package com.assignment.spring.controller;

import com.assignment.spring.dto.Main;
import com.assignment.spring.dto.Sys;
import com.assignment.spring.dto.WeatherResponse;
import com.assignment.spring.orm.WeatherRepository;
import com.assignment.spring.services.iservice.IWeatherService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class WeatherControllerTest {

    private static final Integer TEST_ID = 123;
    private static final String CITY_NAME = "name";
    private static final Double TEMP = 234.00;
    private static final String COUNTRY = "country";
    private WeatherResponse response;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IWeatherService weatherService;
    @MockBean
    private WeatherRepository weatherRepository;
    @MockBean
    private RestTemplate restTemplate;

    @Before
    public void init() {
        Sys sys = new Sys();
        sys.setCountry(COUNTRY);

        Main main = new Main();
        main.setTemp(TEMP);

        response = new WeatherResponse();
        response.setId(TEST_ID);
        response.setName(CITY_NAME);
        response.setSys(sys);
        response.setMain(main);
    }

    @After
    public void clean() {
    }

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("rest.api.url", "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID=1");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty("rest.api.url");
    }

    @Test
    public void getSwagger_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/swagger-ui.html")
                        .with(httpBasic("user", "pass"))
                        .accept(MediaType.ALL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getWeather_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/weather/buc")
                        .with(httpBasic("admin", "test"))
                        .accept(MediaType.ALL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getWeather_success() throws Exception {
        when(weatherService.insertEntity(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/weather/buc")
                        .with(httpBasic("admin", "password"))
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }
}