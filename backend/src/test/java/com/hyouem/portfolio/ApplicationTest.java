package com.hyouem.portfolio;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ApplicationTest {
 @Test void healthEndpointReturnsStableContract() {
  var controller = new EntryController(mock(JdbcTemplate.class));
  var health = controller.health();
  assertEquals("UP", health.get("status"));
  assertEquals("reservas-salas", health.get("project"));
 }
 @Test void rejectsMissingRequiredFields() {
  var controller=new EntryController(mock(JdbcTemplate.class));
  assertThrows(IllegalArgumentException.class,()->controller.create("demo",Map.of()));
 }
 @Test void rejectsInvalidWorkspace() {
  var controller=new EntryController(mock(JdbcTemplate.class));
  assertThrows(IllegalArgumentException.class,()->controller.list("!"));
 }
 @Test void returns404WhenEntryNotFound() {
  var jdbc=mock(JdbcTemplate.class);
  when(jdbc.update("delete from entries where id=? and tenant=?",99L,"demo")).thenReturn(0);
  var controller=new EntryController(jdbc);
  var exception=assertThrows(ResponseStatusException.class,()->controller.delete("demo",99L));
  assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());
 }
}
