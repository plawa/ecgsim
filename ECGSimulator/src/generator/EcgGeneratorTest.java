package generator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import parser.generated.jaxb.Ecg;
import resources.RythmType;

public class EcgGeneratorTest {

	EcgGenerationConfiguration sinusRythmGenerationConfig;
	EcgGenerationConfiguration tachycardiaGenerationConfig;
	EcgGenerationConfiguration bradycardiaGenerationConfig;

	@Before
	public void setUp() throws Exception {
		sinusRythmGenerationConfig = new EcgGenerationConfiguration();
		sinusRythmGenerationConfig.setHeartRate(70);
		sinusRythmGenerationConfig.setRythmType(RythmType.SINUS_RYTHM);
		tachycardiaGenerationConfig = new EcgGenerationConfiguration();
		tachycardiaGenerationConfig.setHeartRate(160);
		tachycardiaGenerationConfig.setRythmType(RythmType.TACHYCARDIA_SUPRAVENTRICULARIS);
		bradycardiaGenerationConfig = new EcgGenerationConfiguration();
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
