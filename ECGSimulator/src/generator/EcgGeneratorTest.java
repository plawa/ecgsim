package generator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import common.enums.DiseaseType;
import parser.generated.jaxb.Ecg;

public class EcgGeneratorTest {

	@Before
	public void setUp() throws Exception {
		// do nothing
	}

	@Test
	public void testGenerateSinusRythm() {
		EcgGenerationParameters sinusRythmConfig = new EcgGenerationParameters()
				.withHeartRate(70)
				.withRythmType(DiseaseType.SINUS_RYTHM);
		Ecg generatedEcg = EcgGenerator.generate(sinusRythmConfig);
		assertCommonProperties(generatedEcg);
	}

	private void assertCommonProperties(Ecg generatedEcg) {
		assertEquals(generatedEcg.getLead().size(), 1);
	}

}
