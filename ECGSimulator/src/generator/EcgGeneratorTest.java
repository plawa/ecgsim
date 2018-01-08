package generator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import parser.generated.jaxb.Ecg;
import resources.RythmType;

public class EcgGeneratorTest {

	EcgGenerationConfig sinusRythmGenerationConfig;
	EcgGenerationConfig tachycardiaGenerationConfig;
	EcgGenerationConfig bradycardiaGenerationConfig;

	@Before
	public void setUp() throws Exception {
		sinusRythmGenerationConfig = new EcgGenerationConfig();
		sinusRythmGenerationConfig.setHeartRate(70);
		sinusRythmGenerationConfig.setRythmType(RythmType.SINUS_RYTHM);
		tachycardiaGenerationConfig = new EcgGenerationConfig();
		tachycardiaGenerationConfig.setHeartRate(160);
		tachycardiaGenerationConfig.setRythmType(RythmType.TACHYCARDIA_SUPRAVENTRICULARIS);
		bradycardiaGenerationConfig = new EcgGenerationConfig();
		bradycardiaGenerationConfig.setHeartRate(40);
		bradycardiaGenerationConfig.setRythmType(RythmType.BRADYCARDIA);
	}

	@Test
	public void testGenerateSinusRythm() {
		Ecg generatedEcg = EcgGenerator.generate(sinusRythmGenerationConfig);
		assertCommonProperties(generatedEcg);
	}

	private void assertCommonProperties(Ecg generatedEcg) {
		assertEquals(generatedEcg.getLead().size(), 12);
	}

}
