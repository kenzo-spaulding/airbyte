//package io.airbyte.server;
//
//import io.airbyte.config.EnvConfigs;
//import io.airbyte.config.StandardWorkspace;
//import io.airbyte.config.persistence.ConfigRepository;
//import io.airbyte.config.persistence.DefaultConfigPersistence;
//import io.airbyte.config.persistence.PersistenceConstants;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//

// TODO Remember to comment out the slf4j imp in the out gradle build
// and uncomment the spring dependency!

//@SpringBootTest
//public class ServerAppInjectionsTest {
//
//  @Spy
//  private final EnvConfigs configs = new EnvConfigs();
//
//  @Mock
//  private DefaultConfigPersistence dcp;
//
//  @Mock
//  private ConfigRepository cp;
//
//  @InjectMocks
//  private ServerAppInjections sai;
//
//  @Test
//  public void contextLoads() {
//
//  }
//
//  @Test
//  public void testRunner() throws Exception {
//    when(configs.getConfigRoot()).thenReturn(null);
//    when(cp.getStandardWorkspace(PersistenceConstants.DEFAULT_WORKSPACE_ID)).thenReturn(new StandardWorkspace());
//
//    sai.start();
//
//    verify(configs).getConfigRoot();
//    verify(configs).getTrackingStrategy();
//    verify(configs).getAirbyteRole();
//    verify(configs).getAirbyteVersion();
//  }
//}


