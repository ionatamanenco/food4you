package restaurant;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantDTO;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.restaurant.RestaurantService;
import ia.ffy.foodforyou.util.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class RestaurantServiceUnitTests {

    @Mock
    RestaurantRepository restaurantRepository;

    RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    void testFindAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("1", "McDonald's", "logo", "fast food"));
        when(restaurantRepository.findAll(Sort.by("id"))).thenReturn(restaurants);

        List<RestaurantDTO> restaurantDTOs = restaurantService.findAll();
        assertEquals(restaurants.size(), restaurantDTOs.size());
        assertEquals(restaurants.get(0).getId(), restaurantDTOs.get(0).getId());
        assertEquals(restaurants.get(0).getCompanyName(), restaurantDTOs.get(0).getCompanyName());
        assertEquals(restaurants.get(0).getCompanyLogo(), restaurantDTOs.get(0).getCompanyLogo());
        assertEquals(restaurants.get(0).getCompanyDescription(), restaurantDTOs.get(0).getCompanyDescription());

        verify(restaurantRepository, times(1)).findAll(Sort.by("id"));
    }

    @Test
    void testGet() {
        String restaurantId = "1";
        Restaurant restaurant = new Restaurant(restaurantId, "McDonald's", "logo", "fast food");
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        RestaurantDTO restaurantDTO = restaurantService.get(restaurantId);
        assertNotNull(restaurantDTO);
        assertEquals(restaurant.getId(), restaurantDTO.getId());
        assertEquals(restaurant.getCompanyName(), restaurantDTO.getCompanyName());
        assertEquals(restaurant.getCompanyLogo(), restaurantDTO.getCompanyLogo());
        assertEquals(restaurant.getCompanyDescription(), restaurantDTO.getCompanyDescription());

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void testGetNotFound() {
        String restaurantId = "1";
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> restaurantService.get(restaurantId));

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void testCreate() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId("1");
        restaurantDTO.setCompanyName("McDonald's");
        restaurantDTO.setCompanyLogo("logo");
        restaurantDTO.setCompanyDescription("fast food");

        Restaurant restaurant = new Restaurant();
        restaurant.setId("1");
        restaurant.setCompanyName("McDonald's");
        restaurant.setCompanyLogo("logo");
        restaurant.setCompanyDescription("fast food");
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        String createdRestaurantId = restaurantService.create(restaurantDTO);
        assertEquals(restaurant.getId(), createdRestaurantId);

        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    @DisplayName("Update restaurant should call repository with correct parameters")
    void testUpdate() {
        // Arrange
        String id = "1";
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(id);
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        when(restaurantRepository.findById(id)).thenReturn(Optional.of(restaurant));

        // Act
        restaurantService.update(id, dto);

        // Assert
        verify(restaurantRepository, times(1)).findById(id);
        verify(restaurantRepository, times(1)).save(restaurant);
        verifyNoMoreInteractions(restaurantRepository);
    }

    @Test
    @DisplayName("Delete restaurant should call repository with correct parameter")
    void testDelete() {
        // Arrange
        String id = "1";

        // Act
        restaurantService.delete(id);

        // Assert
        verify(restaurantRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(restaurantRepository);
    }

    @Test
    @DisplayName("Delete all restaurants should call repository")
    void testDeleteAll() {
        // Act
        restaurantService.deleteAll();

        // Assert
        verify(restaurantRepository, times(1)).deleteAll();
        verifyNoMoreInteractions(restaurantRepository);
    }

    @Test
    @DisplayName("ID exists should call repository with correct parameter")
    void testIdExists() {
        // Arrange
        String id = "1";
        when(restaurantRepository.existsByIdIgnoreCase(id)).thenReturn(true);

        // Act
        boolean result = restaurantService.idExists(id);

        // Assert
        verify(restaurantRepository, times(1)).existsByIdIgnoreCase(id);
        verifyNoMoreInteractions(restaurantRepository);
        assertTrue(result);
    }
}
