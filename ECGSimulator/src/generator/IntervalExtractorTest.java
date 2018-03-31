package generator;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.notNullValue;

public class IntervalExtractorTest {

	@Test
	public void test() {
		List<String> extractedData = IntervalExtractor.extract(signalPart);
		assertThat(extractedData, notNullValue());
	}

	private static final String signalPart = "55 31 25 37 31 19 13 7 13 19 25 25 19 19 19 19 19 19 19 19 13 7 7 13 13 13 7 0 0 7 19 25 25 19 19 31 25 25 25 31 37 13 0 19 31 43 43 31 25 19 19 19 25 37 37 31 31 37 31 13 0 13 37 49 43 43 43 49 55 61 61 80 110 129 165 196 257 318 342 391 440 494 568 604 671 738 787 848 872 915 940 933 866 720 604 440 360 281 165 86 -6 -67 -97 -134 -146 -158 -158 -146 -146 -140 -128 -91 -54 -18 7 7 0 -24 -36 -36 -24 -12 -6 -6 -6 0 -6 0 13 7 0 7 7 7 7 13 13 13 19 19 19 13 13 7 0 0 7 25 25 19 13 19 25 7 0 19 25 31 37 37 31 31 31 31 25 19 19 19 19 19 13 13 13 19 31 37 25 25 25 25 31 31 31 31 31 19 13 13 19 25 37 49 43 43 37 37 31 25 25 31 37 43 43 43 49 55 61 61 55 55 43 37 19 31 68 86 68 55 43 37 37 37 31 37 49 55 55 49 43 37 37 43 55 55 61 61 61 74 92 98 86 74 68 68 68 74 80 92 98 86 74 74 86 92 98 104 104 110 110 104 98 92 86 104 129 129 122 122 135 141 135 141 141 135 135 147 153 159 153 159 159 165 177 183 190 190 183 177 177 177 190 196 196 177 165 165 171 183 202 220 226 208 190 202 220 220 202 196 190 190 196 196 196 190 190 190 196 202 202 202 202 214 214 202 190 190 190 183 159 147 129 116 122 135 122 122 129 135 141 141 141 135 129 122 116 104 98 110 122 116 110 98 86 80 74 68 68 80 98 104 98 92 80 61 49 55 55 68 74 68 61 68 61 49 55 55 55 55 49 43 43 49 49 49 49 49 49 49 49 37 31 37 49 37 25 31 43 61 55 49 37 25 25 43 43 37 25 31 37 37 31 31 49 55 43 25 25 25 37 43 37 31 37 37 37 25 25 49 49 37 19 19 37 49 55 49 55 55 49 37 31 13 13 19 25 37 49 43 25 25 31 37 43 43 43 37 31 19 31 49 55 43 37 37 49 55 55 43 49 49 37 31 37 43 37 31 31 43 49 49 43 31 31 55 55 37 31 37 43 55 55 43 49 61 61 55 49 43 49 55 55 49 49 49 49 49 43 37 43 49 43 43 49 49 37 31 37 49 55 55 49 49 43 37 43 43 37 49 68 68 61 55 55 61 55 49 43 43 49 43 49 55 61 74 74 68 55 43 25 7 7 19 37 43 49 43 31 31 37 37 37 43 43 43 37 31 31 37 43 37 25 19 19 25 31 43 49 43 37 31 31 25 25 19 19 7 19 49 68 61 55 49 49 43 37 37 19 13 13 31 49 55 55 43 43 43 43 37 31 37 37 37 43 43 37 31 37 37 37 37 37 37 43 49 49 55 55 49 49 55 61 55 49 61 61 55 61 74 86 86 74 74 80 86 86 80 61 68 86 80 86 104 98 86 86 98 110 122 129 129 122 116 110 104 98 80 92 116 122 110 104 104 104 98 92 86 80 68 61 55 61 68 74 80 92 98 98 92 92 86 92 98 98 86 80 68 61 55 55 55 61 68 61 49 37 31 31 31 31 37 31 25 19 19 19 19 25 37 31 25 13 13 13 13 19 31 37 31 19 13 19 25 25 19 13 25 37 19 -6 -12 -12 7 19 31 31 25 13 0 7 19 31 43 37 31 19 25 25 25 25 31 31 19 25 31 25 13 13 25 43 31 13 13 31 43 31 43 55 49 31 13 0 0 19 31 37 37 19 7 0 7 25 19 0 7 25 31 25 13 19 31 49 61 68 68 74 86 98 122 153 202 263 287 354 433 482 555 598 647 720 775 842 879 897 915 909 897 799 696 537 391 324 208 135 49 -48 -91 -170 -219 -237 -250 -250 -231 -213 -213 -195 -176 -176 -152 -121 -91 -54 -42 -30 -30 -30 -12 7 13 13 13 7 13 19 13 13 13 7 0 0 7 0 7 19 25 25 19 19 31 49 49 37 31 25 25 25 25 19 13 25 25 31 37 31 19 7 7 25 25 31 37 31 19 19 19 19 7 0 7 25 31 31 37 37 43 43 31 25 31 37 49 43 49 49 43 43 37 43 43 37 49 55 49 49 43 43 31 25 25 31 37 37 49 74 61 37 37 43 49 68 74 68 68 68 55 43 37 55 74 74 55 37 55 74 86 74 68 74 74 68 74 86 74 61 61 61 74 86 98 110 104 98 86 92 98 98 98 98 92 116 135 135 141 147 153 147 141 135 135 141 159 171 159 147 147 147 153 171 190 190 196 196 190 190 190 183 183 183 190 190 190 190 196 202 214 220 220 226 232 226 220 214 214 226 226 226 226 226 226 226 226 220 214 208 208 226 232 226 220 220 220 202 190 202 214 196 196 214 214 214 220 214 202 202 202 196 196 190 177 165 171 190 183 165 165 171 171 153 129 122 122 129 122 116 110 98 98 110 116 104 104 98 92 80 68 61 74 86 104 98 80 68 68 68 68 74 68 68 74 68 68 61 61 68 80 86 74 68 55 55 49 49 49 49 49 49 49 37 25 25 31 37 43 43 37 31 31 31 31 43 43 43 37 43 43 31 31 37 37 43 49 43 37 37 31 37 55 49 31 31 19 19 31 49 43 49 43 43 31 31 31 37 37 31 31 49 61 61 49 31 25 19 31 49 61 68 68 68 61 55 55 55 49 49 61 68 61 61 68 74 68 55 55 55 61 61 61 55 55 61 68 74 68 68 74 68 61 55 55 61 68 74 74 74 74 80 80 68 55 68 80 86 80 68 68 74 68 68 61 55 61 68 68 61 55 61 68 68 68 68 61 68 68 61 49 43 49 55 55 49 49 43 37 43 55 61 86 92 80 68 55 55 49 49 55 61 61 61 61 61 55 49 49 68 68 68 61 49 49 61 74 80 68 61 61 68 61 55 55 55 55 43 37 49 55 49 31 37 61 74 68 68 61 61 55 55 49 49 55 49 49 43 43 43 49 49 43 43 55 61 55 55 68 68 68 61 43 37 55 74 74 92 104 98 104 110 110 104 104 92 80 80 74 74 74 80 86 86 92 104 104 98 92 92 98 104 104 98 104 110 110 98 98 104 110 116 116 122 141 153 135 122 110 110 92 80 80 86 86 98 104 110 104 104 110 104 98 104 104 98 92 86 86 92 98 92 86 86 74 68 61 61 68 61 49 43 43 37 31 31 37 37 43 55 68 68 61 55 49 49 55 55 55 43 37 37 43 43 43 55 61 49 43 37 37 37 37 43 43 37 37 43 43 49 43 37 49 61 61 55 49 49 43 49 55 61 61 49 31 19 13 13 25 37 31 31 37 43 37 31 31 31 25 25 37 31 31 43 49 37 31 37 43 49 49 37 25 25 43 49 37 43 61 74 68 55 49 55 61 55 49 43 49 68 74 86 92 104 135 159 208 257 299 366 397 452 500 525 598 659 708 769 805 842 854 866 854 805 708 537 440 311 214 141 19 -54 -97 -146 -176 -213 -237 -243 -243 -231 -213 -189 -170 -164 -158 -152 -121 -85 -60 -30 -18 0 7 13 19 19 0 7 25 25 19 19 19 25 37 55 61 43 19 19 19 13 19 19 25 43 49 25 0 7 25 43 37 31 37 37 37 37 37 37 37 43 43 43 43 43 43 43 37 31 25 37 37 37 43 49 49 49 43 43 49 49 49 43 43 49 49 61 61 61 49 49 68 68 55 49 43 49 55 55 55 49 49 55 55 55 61 68 68 68 68 61 55 55 55 55 61 61 68 74 74 68 68 61 61 68 68 74 86 98 92 80 80 80 86 86 86 98 98 98 98 98 92 86 98 110 92 74 86 104 122 129 135 135 116 104 110 122 129 135 135 129 147 159 153 147 147 147 147 147 147 147 153 165 171 165 159 165 177 202 214 214 208 208 208 208 214 220 220 214 208 202 202 202 220 226 226 220 208 202 202 214 226 232 232 232 232 214 214 220 226 214 208 208 214 226 220 214 202 202 202 208 202 202 202 196 196 196 196 190 183 171 165 171 171 171 165 159 153 141 153 171 159 147 135 141 147 141 135 122 92 92 116 122 110 104 116 129 116 92 92 80 61 55 49 55 68 80 74 68 61 55 43 37 43 49 49 43 37 55 49 37 31 37 43 37 31 19 19 25 43 49 43 37 37 49 43 37 31 25 25 31 37 37 31 25 25 25 25 25 25 31 25 25 25 25 25 19 19 19 25 25 25 25 25 25 25 19 25 31 31 31 37 31 25 25 19 25 37 37 25 19 25 31 49 49 37 25 31 31 31 37 37 37 55 49 25 19 37 49 61 61 61 68 74 68 49 37 49 68 68 68 74 74 68 61 55 49 43 37 37 37 37 37 49 61 68 61 55 55 55 55 55 55 55 55 49 49 43 43 37 37 37 37 37 31 37 43 49 49 49 55 61 55 43 37 25 25 31 43 37 37 37 43 43 49 49 49 55 43 31 37 37 37 31 31 31 31 37 43 49 49 43 43 43 31 25 25 31 37 37 31 31 31 37 37 31 25 25 31 31 37 31 19 25 43 43 37 37 37 31 19 13 25 43 49 43 37 37 43 43 43 43 37 37 37 37 37 25 19 19 37 61 61 31 19 13 19 13 13 7 13 19 25 31 31 31 31 37 43 43 43 37 37 37 49 49 49 49 49 49 55 55 55 61 68 61 49 43 49 49 55 55 61 74 80 86 80 80 80 74 74 74 80 80 80 86 86 80 80 86 92 110 110 110 104 98 92 74 68 61 61 61 68 68 61 55 55 68 80 74 68 61 55 55 55 61 61 68 68 61 49 49 61 55 43 31 19 19 19 19 7 -6 -6 7 19 13 13 13 7 0 7 7 7 13 19 19 25 31 31 31 37 37 31 13 7 -6 -24 -30 -24 -18 -6 7 0 0 -6 -6 -6 0 0 0 7 0 -6 0 0 0 7 0 -6 -6 -6 0 7 0 0 0 0 7 7 0 0 0 0 0 0 0 0 0 0 -6 -6 0 7 25 25 13 0 7 7 7 19 13 0 0 7 13 13 0 0 0 0 7 13 13 13 13 25 49 74 74 110 147 183 220 250 293 330 403 494 531 592 653 696 769 811 842 879 891 897 830 757 604 488 397 287 232 110 13 -24 -67 -91 -121 -164 -182 -195 -182 -170 -146 -140 -128 -121 -109 -85 -67 -54 -36 -30 -36 -24 -12 0 0 0 0 0 13 7 -6 -18 -24 -24 -24 -24 -30 -18 -6 0 7 7 0 0 0 0 0 0 0 -6 -12 -12 -12 -12 -6 0 0 0 0 7 7 0 0 0 0 7 7 7 0 0 0 0 0 0 -12 -12 -6 7 7 7 7 7 0 0 0 7 13 19 19 19 13 13 0 0 19 19 13 19 13 7 0 7 13 13 19 13 31 49 43 31 31 25 19 13 13 25 37 37 19 13 13 19 19 19 25 37 49 49 43 31 25 25 31 37 37 37 37 43 43 49 37 31 25 19 25 37 55 61 55 55 68 68 68 74 80 80 86 98 98 86 74 80 80 74 74 80 86 86 92 104 116 122 122 122 116 116 116 122 129 129 129 129 129 141 153 159 159 159 153 159 165 171 171 165 165 165 165 165 165 159 165 171 183 183 177 171 171 171 165 159 159 171 171 165 159 153 153 147 159 165 171 165 153 153 153 147 141 147 153 153 153 147 147 147 141 122 98 92 98 110 104 104 98 98 98 92 80 68 61 61 61 61 61 61 61 55 43 31 25 31 43 55 55 49 43 43 37 37 31 25 25 25 25 19 7 0 7 25 43 37 31 37 31 25 13 13 19 19 13 7 7 0 0 0 -6 0 7 13 13 7 0 19 31 31 7 -24 -12 19 19 13 7 13 19 13 7 13 13 13 13 13 7 7 0 7 7 0 0 0 -6 0 0 0 0 7 7 13 13 13 7 7 7 0 0 -12 -18 0 19 13 7 7 19 19 7 0 -6 0 13 13 7 -6 -6 0 7 13 0 -6 0 0 -6 -6 -6 0 0 7 25 31 25 19 19 19 19 19 25 19 13 0 0 0 0 7 19 13 7 25 25 13 13 19 25 25 25 19 13 13 7 7 13 19 13 13 13 13 13 7 7 13 13 7 7 0 0 0 0 7 7 0 0 0 0 7 13 19 13 7 7 13 7 13 13 13 13 13 13 13 7 0 7 7 0 -6 -6 0 7 7 7 0 0 7 0 0 19 25 13 7 7 0 7 13 13 13 13 7 7 0 7 7 0 13 37 37 19 7 0 -6 -18 -18 -6 0 7 13 0 -12 -12 -6 -6 0 0 -12 -30 -30 -24 -12 -6 7 13 7 0 0 7 7 0 0 0 0 0 7 7 0 -6 -6 0 13 19 19 25 25 13 13 13 7 13 13 19 19 13 19 25 37 43 49 43 37 31 25 19 19 25 25 31 31 31 37 37 43 61 74 74 68 61 55 55 49 49 55 68 74 74 74 74 74 80 74 55 37 37 43 55 55 61 68 68 61 55 37 37 49 55 49 49 43 43 49 55 55 49 43 37 43 49 37 13 13 -12 -30 -42 -30 -12 -12 -24 -30 -24 -12 -12 -12 -12 -18 -24 -24 -24 -24 -6 0 -6 -12 -12 -12 -18 -24 -24 -6 0 -12 -30 -12 -6 -18 -30 -30 -24 -18 -18 -18 -18 -12 -12 -18 -24 -24 -18 -18 -18 -18 -18 -24 -24 -24 -24 -24 -30 -30 -30 -24 -18 -24 -30 -36 -30 -18 -12 -18 -24 -18 -12 -18 -24 -24 -18 -18 -18 -12 -12 -18 -18 -6 -6 -6 -12 -18 -12 -12 -18 -24 -24 -18 -12 -6 0 0 0 0 0 13 31 55 61 43 37 55 74 129 159 220 281 318 391 446 488 549 598 671 744 793 842 848 866 866 848 775 641 543 397 305 214 92 13 -73 -121 -146 -189 -219 -243 -250 -243 -250 -237 -207 -176 -164 -158 -134 -115 -85 -67 -60 -54 -54 -48 -42 -42 -36 -30 -24 -24 -24 -24 -24 -24 -24 -24 -36 -30 -30 -30 -36 -42 -48 -42 -24 -12 -12 -30 -36 -24 -36 -36 -12 0 -12 -18 -18 -12 -18 -24 -18 -12 -12 -18 -30 -24 -18 -12 -6 -18 -24 -30 -24 -18 -24 -18 0 7 0 -6 -12 -6 0 0 0 0 0 0 0 7 -18 -30 -24 -12 0 0 0 0 7 7 0 -12 -18 -18 -12 -12 -6 -6 0 0 7 7 0 0 7 7 0 7 13 13 13 13 13 7 7 7 7 7 13 19 19 13 0 0 13 19 19 19 19 19 19 19 19 43 61 49 43 37 37 37 43 55 68 68 68 68 68 68 68 74 80 86 80 68 80 86 80 80 86 98 92 86 98 116 129 135 141 135 135 135 141 141 141 153 153 147 141 141 147 171 183 183 171 171 171 165 171 177 190 177 177 190 190 183 171 159 165 165 171 177 171 165 171 171 171 171 171 165 159 159 165 165 159 159 153 147 141 147 153 153 153 147 147 141 135 129 129 122 116 122 129 122 116 110 98 74 61 74 80 68 61 55 49 55 61 61 68 68 49 37 37 43 43 43 31 25 13 13 19 25 37 31 25 25 19 13 -6 -12 0 7 7 0 13 25 19 13 13 7 0 0 0 0 -12 -18 -18 0 13 13 7 0 7 13 13 7 7 0 0 0 0 0 0 0 -6 -18 -18 -6 0 13 13 19 13 -6 -12 -12 -12 -12 -6 0 7 7 7 7 0 -6 -6 0 0 -12 -12 -6 -6 0 0 -6 -24 -36 -30 -12 0 7 7 7 7 0 0 0 0 0 -6 -6 7 13 7 -6 0 0 -6 7 13 0 7 13 0 0 0 7 0 0 0 0 0 7 13 19 13 7 13 25 19 13 7 0 7 13 19 19 25 25 19 19 19 13 7 7 7 7 7 13 25 31 19 0 0 0 13 25 37 31 25 25 19 7 0 0 13 19 19 7 7 7 7 7 7 7 13 13 13 13 19 19 19 13 13 13 19 7 -6 -18 -24 -18 -6 13 19 31 31 31 19 7 0 0 0 0 0 0 0 0 0 0 7 13 7 -12 -12 0 7 0 0 0 0 0 0 7 0 7 19 13 0 0 7 13 13 7 19 19 13 7 7 0 7 7 7 0 0 0 7 13 7 0 0 7 7 0 0 0 0 7 13 7 0 0 0 7 13 19 31 49 49 37 25 31 43 43 31 25 37 31 25 25 25 31 43 49 49 49 49 55 61 61 55 55 55 61 68 80 74 68 68 68 74 74 80 80 68 61 68 61 49 43 37 43 43 37 31 43 55 55 55 55 43 43 49 68 68 49 31 37 13 7 19 25 19 25 19 13 7 13 19 13 0 0 0 0 0 0 0 0 0 -6 -12 -12 -6 0 0 -6 -6 -6 -12 -12 -24 -30 -24 -18 -12 0 7 0 0 -6 -6 -12 -18 -6 -6 -12 -18 -24 -24 -24 -18 -12 -12 -6 -12 -18 -18 -18 -18 -12 -12 -12 -18 -24 -12 -6 -6 -18 -24 -18 -12 -12 -12 -18 -18 -12 -12 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 -6 0 7 0 0 -6 0 7 0 -12 -24 -30 -54 -42 -6 -6 -12 -6 13 25 19 13 13 7 19 31 55 80 122 190 220 263 311 354 421 458 507 574 610 677 732 757 787 799 805 763 714 604 452 360 226 135 37 -73 -115 -164 -207 -243 -286 -298 -310 -317 -317 -298 -268 -250 -237 -225 -189 -146 -128 -103 -85 -73 -54 -42 -42 -36 -24 -24 -24 -24 -30 -36 -36 -36 -36 -24 -18 -18 -12 -12 -12 -6 0 0 0 -12 -24 -24 -12 -6 -12 -24 -36 -36 -24 -6 -6 0 7 0 -6 -6 -6 0 7 7 0 0 0 -6 -6 0 7 7 7 7 0 -6 -6 7 13 13 7 -6 -6 0 25 37 37 19 13 7 7 0 0 0 7 7 0 0 0 0 0 0 7 7 7 13 13 7 7 13 25 37 31 31 25 31 31 31 25 19 19 31 37 37 25 25 31 43 49 37 37 43 43 37 43 49 49 37 25 25 37 61 68 61 61 68 68 55 55 68 74 74 68 61 68 68 68 74 92 104 110 110 104 104 110 116 116 110 104 122 141 141 116 104 116 141 153 165 159 159 153 153 153 159 165 165 165 171 171 171 171 183 196 190 171 165 171 171 177 183 183 190 190 190 183 177 171 171 177 171 159 147 141 147 153 165 177 177 171 171 159 147 141 141 147 141 135 141 135 141 159 159 141 135 135 135 129 116 110 110 104 98 92 92 86 86 92 86 80 68 61 61 68 68 68 61 49 43 37 31 37 43 49 49 37 25 25 19 19 13 13 0 -6 -6 0 13 37 43 19 0 7 7 7 7 0 0 0 0 0 0 -6 -6 -6 -12 -30 -30 -12 -6 0 13 25 19 7 0 -6 0 0 0 0 0 0 -6 -6 -6 -12 -6 0 0 -6 -12 -12 -18 -24 -18 -12 -12 0 0 -6 -12 -18 -18 -18 -18 -6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 -6 0 0 0 7 13 13 19 19 19 13 0 7 13 19 13 7 7 13 13 19 19 19 13 13 7 13 31 37 25 25 13 13 7 19 43 37 25 25 31 37 37 31 19 13 13 19 19 19 7 7 13 19 19 13 13 25 31 25 19 19 19 19 13 7 7 7 13 13 7 7 0 0 0 7 19 7 0 0 0 7 19 25 19 13 7 7 13 19 25 19 13 7 0 7 7 0 -6 -12 -6 0 0 0 13 25 13 0 -6 -12 -12 0 0 0 0 -6 -12 -12 -6 0 7 13 13 13 7 0 0 -6 -6 -6 0 0 7 0 7 13 25 19 7 -6 -12 -6 0 -6 -12 -12 -12 -6 -6 0 0 -12 -18 -18 -6 13 25 25 7 7 0 0 0 0 0 -12 -12 -6 7 19 31 31 13 13 19 13 13 13 7 7 13 25 31 31 31 31 19 13 7 0 13 31 43 49 55 55 55 55 55 55 61 68 68 68 55 55 61 68 74 74 80 92 98 86 68 61 49 49 43 43 43 31 19 19 19 19 31 43 43 43 37 37 43 49 55 49 43 49 43 31 19 13 13 13 7 13 7 -6 -24 -30 -24 -12 -6 0 -6 -6 -6 -6 -6 -6 -6 -18 -24 -24 -24 -18 -12 -6 0 7 0 -12 -24 -18 -18 -18 -18 -18 -6 0 0 -6 -12 -12 -12 -12 -18 -18 -18 -24 -30 -30 -24 -18 -12 -18 -18 -18 -12 -12 -12 -18 -18 -6 -12 -30 -30 -24 -24 -18 -18 -24 -18 -12 -6 0 0 -6 -12 -18 -24 -24 -18 -12 -12 -6 -6 -6 -6 0 0 -24 -36 -36 -24 -24 -36 -42 -36 -12 0 -6 -18 -12 -6 0 7 19 25 37 49 61 86 122 159 214 244 287 330 372 440 482 537 598 629 696 775 818 866 872 872 799 714 586 458 379 263 190 92 -18 -60 -121 -158 -176 -207 -219 -225 -207 -195 -182 -170 -164 -152 -140 -128 -103 -79 -60 -48 -42 -36 -42 -30 -24 -24 -30 -42 -42 -42 -36 -24 -12 -24 -24 -18 -24 -24 -30 -24 -18 -18 -18 -24 -24 -24 -18 -12 -6 -6 -18 -30 -24 -18 -24 -24 -18 -18 -24 -36 -42 -24 0 0 -6 -6 -18 -30 -30 -42 -42 -36 0 31 31 7 7 0 -12 -24 -42 -48 -36 -18 -12 -18 -24 -6 7 0 7 0 -6 -6 -6 -6 -6 0 -12 -18 -6 0 0 0 0 0 0 0 0 0 0 0 0 0 7 7 7 0 -6 0 7 0 7 7 0 0 13 19 25 19 19 19 19 13 13 7 13 13 31 37 31 31 37 37 31 25 25 31 37 37 25 31 43 49 49 49 55 55 61 68 68 61 55 61 74 80 74 74 68 68 74 98 116 135 135 135 122 110 98 104 110 122 129 141 147 141 135 129 129 129 129 147 159 153 141 129 116 122 141 153 159 159 147 141 147 147 141 122 116 135 147 159 165 165 159 153 147 153 147 141 135 129 122 122 129 129 135 135 135 129 116 98 98 116 110 98 92 98 110 110 98 86 74 74 74 74 68 61 55 55 49 43 31 31 43 43 37 37 31 31 31 31 25 25 13 7 13 19 13 0 0 0 0 0 7 13 13 13 0 0 -6 -12 -24 -30 -24 7 31 25 7 7 7 0 0 -12 -24 -18 -6 -6 -6 0 0 0 0 0 0 7 -6 -12 -12 -6 -6 0 0 -6 -6 0 -12 -12 -12 -6 -6 -12 -18 -18 -12 -12 -18 -18 -12 -12 0 7 0 -6 -12 -18 -18 -18 -12 -12 -12 -18 -18 -12 0 0 0 -6 -6 -12 -12 0 -6 -18 -24 -18 -12 -6 -6 -6 -12 -6 0 0 -6 -6 0 0 0 -6 -12 -18 -12 -6 0 -6 -12 -6 -6 -6 0 19 37 31 13 7 0 0 7 13 13 13 7 7 0 0 7 7 7 7 0 -6 -12 -6 0 0 -12 0 7 0 7 7 0 0 -6 -6 7 25 25 13 7 -6 -12 -12 0 7 7 0 0 -6 -6 0 0 -6 -12 -18 -18 -6 0 0 -12 -12 -18 -18 -18 -12 -6 -12 -18 -24 -12 0 -6 -12 -12 0 0 0 -6 -12 -6 0 0 0 -6 0 0 0 -6 -6 -6 -18 -18 0 0 -6 0 -6 -18 -30 -30 -18 -6 0 -6 -12 -12 0 0 -6 -30 -36 -30 -12 0 -6 -6 0 0 0 0 -12 -30 -36 -36 -36 -24 -12 -6 -6 -6 -6 0 7 0 -6 0 -6 -12 -18 -24 -24 -18 -18 -18 -24 -18 -12 -6 -12 -12 0 13 7 -6 -6 -6 -6 -12 -12 0 7 0 7 13 13 25 31 31 25 13 19 25 25 19 7 0 0 0 7 13 13 19 25 31 49 55 37 19 31 43 43 37 25 31 37 49 61 61 49 55 55 49 49 43 31 19 19 25 37 37 37 37 37 31 37 37 37 31 25 31 37 31 25 25 25 25 25 19 7 -6 -12 -6 -6 -12 -18 -30 -36 -30 -18 -36 -30 -18 -12 -24 -30 -30 -24 -36 -30 -24 -24 -24 -24 -18 -18 -24 -30 -30 -36 -36 -30 -24 -30 -30 -36 -42 -48 -42 -30 -42 -54 -42 -30 -30 -30 -18 -6 -12 -24 -24 -18 -12 -30 -36 -30 -30 -36 -48 -30 -6 -18 -36 -42 -48 -42 -48 -42 -30 -36 -48 -42 -30 -24 -30 -36 -36 -30 -30 -30 -30 -30 -36 -42 -42 -36 -30 -36 -48 -48 -30 -12 -12 -12 -18 -30 -30 -18 0 0 -18 -18 -12 -6 0 0 13 31 55 80 110 135 183 250 287 348 391 440 513 568 635 708 769 830 848 885 891 872 793 653 555 403 311 232 116 43 -42 -91 -109 -152 -182 -213 -219 -213 -207 -195 -189 -170 -152 -146 -121 -91 -73 -73 -67 -54 -48 -42 -36 -30 -36 -48 -48 -54 -54 -48 -36 -30 -24 -24 -30 -48 -60 -54 -48 -42 -42 -42 -36 -42 -54 -42 -36 -42 -42 -36 -36 -48 -60 -42 -18 -18 -24 -30 -42 -36 -24 -24 -24 -30 -36 -36 -24 -18 -24 -30 -36 -36 -36 -42 -48 -42 -30 -24 -18 -18 -18 -18 -24 -18 -18 -18 -24 -24 -24 -24 -24 -18 -18 -24 -30 -24 -18 -12 -12 -12 -18 -12 -12 -12 -18 -18 -18 -18 -18 -6 -6 -12 -12 -6 -6 -6 0 0 0 -6 -6 -6 -6 -6 -12 -18 -12 0 0 -6 0 -6 -12 0 7 7 19 37 55 49 37 31 19 7 7 7 7 19 25 37 37 31 31 43 43 55 61 49 37 43 55 61 68 68 68 68 68 68 68 80 98 98 92 80 74 80 98 116 122 129 129 129 129 129 135 147 153 135 116 122 147 159 153 135 129 141 147 153 159 159 153 159 171 177 171 153 141 135 147 159 159 153 147 141 147 153 159 153 141 135 135 141 159 165 159 147 147 135 129 129 129 122 116 110 122 116 110 110 98 92 80 74 80 68 68 74 74 68 68 68 61 55 49 37 25 25 25 25 25 25 25 25 25 37 31 19 7 -6 -18 -18 -6 7 13 0 0 0 0 7 13 7 0 -6 -6 -24 -42 -30 0 7 13 13 13 7 0 0 -6 -12 -18 -18 -18 -24 -24 -42 -30 -6 7 0 -6 -12 -18 -24 -24 -12 -6 -6 -12 -12 -12 -18 -30 -30 -36 -30 -24 -18 -12 -12 -6 -6 -12 -12 -12 -18 -24 -36 -42 -36 -30 -30 -24 -24 -30 -30 -24 -18 -12 -12 -12 -12 -18 -18 -30 -48 -48 -36 -24 -12 -6 0 7 0 -24 -36 -24 -12 -6 0 0 -6 -18 -24 -12 -12 -18 -12 -12 -12 -6 7 0 7 13 0 -18 -24 -6 7 0 -6 -12 -24 -24 -24 -12 0 0 0 -6 0 0 0 -6 -12 -6 -6 -6 0 0 -6 -6 -6 0 0 0 0 -6 -12 -12 -6 -6 -6 -6 -6 -18 -18 -12 -6 -12 -12 -6 0 -6 -12 -12 -6 -6 0 0 -12 -12 -6 0 -6 -12 -12 -12 -12 -18 -24 -18 -12 -6 -6 -6 0 0 0 -18 -30 -18 -30 -42 -24 -6 0 -6 -30 -48 -42 -12 13 13 -6 -12 -6 0 0 -6 -12 -12 -6 -12 -18 -18 -6 0 0 -6 0 -6 -18 -18 -24 -24 -18 -18 -24 -24 -18 -24 -24 -18 -12 0 0 7 0 -12 -24 -30 -30 -24 -18 -6 -6 -6 -6 -6 -6 -12 -18 -30 -48 -48 -48 -36 -6 7 0 -12 -24 -24 -12 0 7 7 7 7 7 7 13 19 13 7 7 13 19 13 0 0 25 43 37 37 31 31 31 31 19 13 19 25 25 13 25 43 49 43 37 55 74 74 61 49 25 25 43 49 49 55 49 37 31 31 31 31 31 37 31 25 25 25 19 13 13 19 25 25 25 25 19 0 -18 -30 -36 -30 -18 -12 -18 -12 -6 -12 -18 -24 -36 -48 -42 -36 -36 -42 -36 -30 -30 -30 -18 -18 -24 -24 -24 -30 -30 -36 -36 -36 -36 -42 -42 -30 -42 -42 -30 -12 -12 -24 -36 -30 -36 -36 -30 -30 -30 -24 -24 -30 -48 -54 -42 -42 -36 -30 -36 -42 -18 -12 -24 -36 -42 -48 -42 -42 -54 -67 -60 -48 -36 -42 -48 -48 -42 -36 -36 -30 -18 0 0 -24 -42 -48 -42 -36 -30 -24 -36 -42 -30 -24 -42 -36 -18 -30 -30 -24 -18 -18 -18 -18 -18 -18 -18 -12 -6 7 19 49 80 98 147 196 220 269 318 385 440 476 537 580 641 714 744 781 793 805 787 720 616 440 336 214 110 37 -85 -152 -207 -262 -298 -341 -359 -359 -347 -335 -329 -310 -292 -274 -256 -243 -207 -170 -140 -121 -121 -103 -73 -67 -60 -54 -48 -54 -60 -67 -60 -54 -48 -60 -60 -54 -48 -42 -42 -42 -36 -36 -42 -54 -60 -60 -48 -30 -12 -6 -6 -12 -18 -24 -18 -18 -12 -12 -18 -24 -24 -12 -12 -18 -42 -48 -30 -12 -18 -18 -12 -6 -6 -6 -12 -24 -36 -36 -30 -24 -24 -30 -30 -36 -42 -36 -24 -30 -30 -24 -30 -30 -24 -6 0 -12 -18 -12 -6 0 0 0 -6 -6 -12 -12 -12 -12 -12 -6 0 0 7 7 7 0 0 -12 -24 -6 13 13 7 0 7 7 13 13 7 7 7 7 13 19 25 25 19 13 7 7 7 0 7 19 31 37 25 31 49 61 61 61 61 68 55 49 55 61 68 61 68 74 80 80 68 61 61 61 74 86 92 98 110 122 129 122 122 129 129 122 116 110 110 129 135 135 141 153 153 153 147 147 147 147 153 153 147 141 141 153 159 153 141 129 135 141 147 153 147 147 141 135 141 141 141 147 147 141 135 135 141 141 135 135 129 135 147 147 129 104 86 98 116 129 122 110 110 98 92 92 86 74 68 74 74 61 43 37 55 74 74 55 37 37 55 61 49 37 31 25 19 19 13 7 0 7 -6 -12 0 0 0 7 0 -6 -6 -6 -6 -6 -6 -12 -6 -6 -18 -30 -24 -24 -30 -36 -30 -24 -18 -18 -24 -24 -12 -6 -12 -18 -18 -24 -30 -30 -30 -30 -30 -24 -18 -12 -18 -18 -6 -6 -12 -24 -36 -36 -30 -30 -36 -30 -18 -12 -6 0 -6 -24 -42 -54 -48 -36 -30 -30 -36 -42 -42 -24 -18 -24 -24 -18 -18 -18 -24 -30 -30 -18 -18 -18 -24 -18 -6 0 0 -6 0 0 0 0 0 0 0 0 0 -12 -18 -12 7 7 7 0 -6 -6 -6 0 0 0 0 0 0 -6 -6 -12 -6 0 0 0 -6 7 25 19 0 0 0 -6 -12 -18 -12 -12 -12 0 7 7 7 0 0 -6 -12 -12 -24 -24 -18 -6 -6 -12 -6 0 0 0 0 -6 -12 -18 -12 -6 -6 -12 -12 0 0 0 -6 -12 -18 -24 -30 -30 -30 -18 -18 -24 -24 -18 -12 -18 -24 -24 -18 -30 -30 -30 -24 -30 -30 -24 -12 -6 -12 -18 -30 -30 -18 -6 0 0 -6 -12 -12 -18 -18 -18 -12 -6 -12 -30 -48 -42 -24 -12 -12 -12 -18 -24 -24 -24 -18 -30 -42 -36 -30 -30 -30 -30 -36 -42 -42 -24 -6 -6 -6 -6 -12 -18 -24 -24 -24 -24 -36 -36 -30 -24 -24 -24 -24 -30 -12 0 0 0 -12 -12 -12 -12 -18 -18 -6 -6 0 0 0 0 0 0 13 7 7 7 7 7 19 25 7 0 0 13 25 31 37 37 31 31 31 31 37 37 37 31 31 31 31 43 49 49 43 19 0 19 43 31 13 7 13 13 0 0 0 -6 0 13 31 37 25 25 25 19 13 13 7 0 -6 -12 -18 -18 -12 0 0 -12 -24 -36 -36 -36 -24 -18 -24 -30 -24 -30 -36 -42 -42 -48 -48 -36 -18 -30 -48 -42 -30 -24 -30 -36 -42 -42 -36 -30 -48 -60 -48 -36 -42 -48 -48 -48 -42 -42 -42 -48 -48 -48 -48 -42 -36 -42 -42 -42 -36 -42 -48 -36 -36 -36 -42 -42 -48 -42 -24 -18 -18 -24 -30 -36 -36 -48 -60 -67 -60 -48 -42 -48 -54 -54 -48 -54 -60 -67 -48 -36 -48 -42 -30 -30 -30 -36 -42 -42 -30 -36 -36 -30 -30 -42 -30 -24 -18 -18 -18 -24 -12 7 7 13 25 55 104 129 190 232 281 348 391 446 494 531 610 671 732 781 793 811 805 799 708 586 464 324 257 147 43 -12 -91 -128 -152 -201 -231 -256 -262 -262 -262 -250 -219 -189 -182 -164 -140 -134 -115 -103 -103 -91 -73 -60 -60 -54 -48 -48 -48 -54 -54 -60 -60 -54 -48 -54 -54 -54 -54 -54 -54 -48 -42 -42 -54 -48 -30 -24 -36 -36 -42 -48 -48 -48 -48 -48 -36 -18 -12 -24 -30 -36 -42 -67 -79 -67 -48 -36 -36 -48 -48 -42 -30 -24 -18 -24 -24 -24 -24 -30 -42 -42 -30 -24 -24 -36 -48 -30 -12 -24 -42 -42 -24 -12 -24 -36 -42 -42 -24 -12 -6 -18 -30 -24 -18 -30 -30 -18 -12 -12 -12 -12 -12 -12 -18 -24 -30 -18 -6 7 0 0 -6 -6 -12 -6 0 0 0 0 0 0 0 0 0 0 7 0 0 -6 7 13 0 0 13 25 25 13 13 13 0 13 37 37 31 19 19 25 31 49 61 74 86 86 80 80 86 74 61 68 55 37 61 86 86 86 92 104 104 98 92 86 86 92 110 122 129 129 116 116 116 116 122 122 122 129 129 122 116 110 104 110 116 116 122 129 122 122 122 122 122 122 116 110 110 98 92 86 104 129 141 141 129 129 129 129 122 110 104 110 110 104 98 92 86 80 74 68 74 80 80 74 61 55 37 31 49 68 61 61 37 25 37 49 43 25 19 13 19 25 13 0 0 0 0 0 7 0 -12 -18 -6 0 0 -6 0 0 -6 -6 7 0 -18 -18 -6 0 0 0 0 -6 -18 -24 -30 -36 -54 -60 -36 -36 -42 -42 -42 -36 -36 -36 -36 -24 -18 -30 -30 -30 -48 -60 -60 -54 -36 -24 -18 -24 -24 -18 -12 -12 -18 -18 -24 -36 -42 -42 -42 -42 -42 -42 -42 -42 -36 -30 -30 -36 -36 -24 -24 -36 -36 -36 -30 -30 -36 -42 -36 -30 -30 -30 -30 -30 -24 -24 -18 -18 -30 -36 -30 -30 -30 -30 -30 -30 -24 -18 -24 -18 -12 -18 -30 -36 -36 -42 -42 -24 -6 -12 -18 -12 -6 -6 -18 -18 -6 0 7 0 -12 -12 0 -6 -18 -6 -6 -12 -12 0 7 13 7 -6 -12 -12 -12 -18 -12 -6 0 0 -18 -36 -42 -18 0 7 -12 -24 -42 -42 -36 -30 -24 -18 -12 -12 -18 -18 -18 -24 -30 -30 -24 -24 -18 -12 -12 -18 -24 -30 -30 -30 -30 -30 -24 -30 -36 -36 -30 -30 -18 -12 -30 -42 -30 -24 -18 -18 -18 -24 -24 -24 -24 -30 -30 -24 -30 -36 -30 -18 -24 -24 -36 -60 -60 -30 -6 0 -18 -30 -30 -30 -24 -18 -24 -36 -36 -30 -30 -48 -36 -18 -30 -42 -42 -36 -30 -24 -24 -18 -24 -30 -24 -24 -36 -36 -42 -42 -30 -18 -18 -18 -18 -12 -12 -18 -24 -36 -42 -30 -18 -24 -30 -36 -36 -42 -42 -42 -36 -30 -36 -42 -54 -54 -42 -36 -36 -36 -36 -30 -36 -30 -18 7 19 13 0 0 -6 -12 -12 -6 0 0 -6 -6 0 0 -6 -12 -12 0 7 7 13 19 19 19 13 0 13 31 31 31 31 31 31 25 25 25 13 13 37 37 25 37 25 0 7 19 13 19 25 13 13 19 13 7 0 0 7 19 25 19 25 31 25 19 13 7 7 0 -18 -36 -36 -30 -24 -30 -30 -30 -36 -48 -48 -42 -30 -30 -30 -30 -36 -42 -42 -42 -36 -36 -48 -60 -48 -42 -48 -60 -79 -85 -79 -67 -73 -73 -67 -60 -60 -67 -60 -48 -48 -48 -48 -48 -48 -42 -42 -48 -48 -54 -60 -60 -60 -60 -60 -54 -54 -54 -60 -54 -54 -54 -54 -54 -67 -73 -73 -67 -60 -54 -48 -48 -54 -48 -48 -36 -42 -67 -85 -73 -36 -24 -54 -67 -60 -54 -48 -48 -42 -48 -60 -67 -60 -54 -48 -48 -54 -60 -60 -54 -36 -36 -30 -36 -36 -30 -18 0 19 31 74 110 141 190 226 281 348 391 458 500 561 653 708 781 830 848 872 866 836 708 604 458 342 293 183 86 -12 -85 -97 -134 -176 -201 -225 -225 -219 -213 -213 -195 -176 -170 -152 -134 -109 -103 -115 -115 -91 -60 -48 -60 -73 -79 -79 -79 -73 -79 -79 -73 -67 -60 -60 -67 -79 -85 -73 -60 -54 -60 -73 -79 -85 -91 -91 -91 -79 -60 -48 -42 -48 -54 -48 -42 -48 -54 -60 -60 -60 -60 -54 -60 -60 -67 -60 -60 -60 -54 -48 -48 -48 -54 -60 -60 -60 -60 -54 -54 -60 -67 -60 -48 -42 -42 -48 -48 -54 -60 -60 -60 -60 -60 -60 -60 -60 -48 -36 -24 -18 -12 -12 -18 -18 -18 -30 -36 -36 -30 -36 -48 -60 -60 -54 -54 -48 -42 -36 -30 -36 -36 -36 -36 -30 -18 -24 -36 -30 -24 -24 -18 -12 0 -6 -18 -24 -30 -30 -24 -12 -6 -6 -6 0 0 -6 -6 -6 -6 -6 -12 -12 -6 0 25 37 31 25 25 25 25 31 19 25 49 68 55 49 49 61 55 49 61 68 74 80 86 80 86 92 86 98 116 116 110 110 104 104 110 116 116 116 116 110 110 122 135 141 135 122 122 135 135 129 122 116 116 122 122 110 116 122 122 129 135 141 141 135 129 122 135 129 122 135 122 104 80 74 80 86 86 86 80 80 80 86 80 68 68 55 55 61 61 61 68 68 49 37 25 25 25 19 19 7 0 0 -6 0 0 0 -18 -12 7 7 0 -6 -12 -6 -12 -12 -18 -12 -12 -18 -30 -30 -24 -24 -30 -18 -30 -48 -48 -36 -36 -36 -30 -30 -24 -18 -24 -36 -36 -30 -30 -24 -36 -48 -60 -67 -60 -60 -54 -30 -24 -36 -42 -48 -54 -67 -67 -60 -48 -36 -24 -18 -24 -36 -42 -42 -42 -42 -42 -36 -42 -36 -30 -30 -30 -36 -36 -42 -42 -48 -54 -60 -67 -67 -79 -91 -91 -60 -36 -24 -30 -42 -48 -54 -54 -67 -79 -60 -54 -60 -42 -24 -24 -24 -30 -36 -48 -42 -42 -48 -54 -60 -60 -48 -36 -36 -36 -48 -60 -60 -48 -36 -30 -36 -36 -30 -30 -30 -30 -30 -30 -24 -24 -36 -54 -54 -36 -30 -24 -30 -36 -36 -30 -18 -6 -12 -24 -24 -30 -30 -30 -24 -24 -24 -30 -36 -42 -42 -48 -42 -42 -36 -36 -42 -42 -36 -42 -30 -18 -12 -24 -42 -54 -48 -42 -30 -24 -30 -36 -36 -36 -30 -18 -12 -18 -30 -54 -67 -67 -60 -79 -67 -42 -30 -30 -30 -24 -24 -30 -42 -42 -42 -42 -42 -36 -36 -42 -48 -48 -42 -36 -36 -36 -30 -30 -36 -42 -48 -54 -54 -36 -24 -24 -36 -48 -60 -67 -60 -42 -24 -18 -24 -30 -36 -36 -36 -42 -48 -48 -48 -54 -48 -48 -54 -48 -42 -42 -42 -36 -30 -36 -36 -36 -42 -48 -60 -60 -60 -48 -36 -36 -36 -24 -18 -24 -30 -42 -42 -42 -36 -30 -30 -36 -30 -18 -6 -12 -24 -24 -24 -24 -24 -30 -30 -24 -12 -6 0 7 19 25 19 7 0 7 13 19 19 7 -6 7 25 19 13 13 19 31 37 37 25 25 19 7 -6 -18 -24 -6 0 -6 -24 -30 -24 -6 0 25 25 13 0 0 0 0 -6 -12 -18 -18 -12";

}