package sitting_tables;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import ia.ffy.foodforyou.restaurant.Restaurant;
import ia.ffy.foodforyou.restaurant.RestaurantRepository;
import ia.ffy.foodforyou.sitting_tables.SittingTables;
import ia.ffy.foodforyou.sitting_tables.SittingTablesDTO;
import ia.ffy.foodforyou.sitting_tables.SittingTablesRepository;
import ia.ffy.foodforyou.sitting_tables.SittingTablesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

@ExtendWith(MockitoExtension.class)
class SittingTablesServiceTest {

    @Mock
    SittingTablesRepository sittingTablesRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    SittingTablesService sittingTablesService;

    @Test
    void testFindAll() {
        SittingTables table1 = new SittingTables("1", 4, "QR1");
        SittingTables table2 = new SittingTables("2", 2, "QR2");
        Mockito.when(sittingTablesRepository.findAll((Example<SittingTables>) Mockito.any()))
                .thenReturn(List.of(table1, table2));
        List<SittingTablesDTO> tables = sittingTablesService.findAll();
        assertEquals(2, tables.size());
        assertEquals(table1.getId(), tables.get(0).getId());
        assertEquals(table2.getId(), tables.get(1).getId());
    }

    @Test
    void testGet() {
        SittingTables table = new SittingTables("1", 4, "QR1");
        Mockito.when(sittingTablesRepository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(table));
        SittingTablesDTO tableDto = sittingTablesService.get(table.getId());
        assertEquals(table.getId(), tableDto.getId());
        assertEquals(table.getNumber(), tableDto.getNumber());
        assertEquals(table.getQrCode(), tableDto.getQrCode());
    }

    @Test
    void testCreate() {
        SittingTablesDTO tableDto = new SittingTablesDTO();
        tableDto.setNumber(2);
        tableDto.setQrCode("QR3");
        Mockito.when(restaurantRepository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(new Restaurant()));
        String id = sittingTablesService.create(tableDto);
        assertNotNull(id);
    }

    @Test
    void testUpdate() {
        SittingTables table = new SittingTables("1", 4, "QR1");
        SittingTablesDTO tableDto = new SittingTablesDTO();
        tableDto.setId("1");
        tableDto.setNumber(2);
        tableDto.setQrCode("QR2");
        Mockito.when(sittingTablesRepository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(table));
        sittingTablesService.update(table.getId(), tableDto);
        assertEquals(tableDto.getNumber(), table.getNumber());
        assertEquals(tableDto.getQrCode(), table.getQrCode());
    }

    @Test
    void testDelete() {
        SittingTables table = new SittingTables("1", 4, "QR1");
        Mockito.when(sittingTablesRepository.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(table));
        sittingTablesService.delete(table.getId());
        Mockito.verify(sittingTablesRepository, Mockito.times(1)).deleteById(table.getId());
    }

    @Test
    void testIdExists() {
        Mockito.when(sittingTablesRepository.existsByIdIgnoreCase(Mockito.anyString()))
                .thenReturn(true);
        assertTrue(sittingTablesService.idExists("1"));
    }

    @Test
    void testNumberExists() {
        Mockito.when(sittingTablesRepository.existsByNumber(Mockito.anyInt()))
                .thenReturn(true);
        assertTrue(sittingTablesService.numberExists(4));
    }
}