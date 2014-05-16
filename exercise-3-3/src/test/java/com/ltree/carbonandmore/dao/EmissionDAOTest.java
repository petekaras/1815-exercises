package com.ltree.carbonandmore.dao;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;
/**
 * Functional Tests that ensure our DAO is working as expected.
 * @author peter
 *
 */
public class EmissionDAOTest {
	EmissionDAO emissionDAO = null;

	/* Test User */
	private static final TransportType TRANSPORT_TYPE_ON_SYSTEM = TransportType.CAR_FORD_GALAXY;
	private static final BigDecimal EXPECTED_EMISSION_FOR_TRANSPORT_TYPE_ON_SYSTEM = new BigDecimal("12.61");

	/* Test User not on system */
	private static final TransportType TRANSPORT_TYPE_NOT_ON_SYSTEM = TRANSPORT_TYPE_ON_SYSTEM.CAR_NISSAN_MICRA;

	@Before
	public void setUp() {
		/*
		 * Here we specify which implementation of the EmissionDAO we are using.
		 * In this case we use an XML based DAO for XML Files
		 */
		emissionDAO = new XMLEmissionDAO();
		emissionDAO.setDataSource("/com/ltree/carbonandmore/dao/emissions.xml");
	}

	@After
	public void tearDown() {
		emissionDAO = null;
	}

	@Test
	public void shouldFindEmission() {
		BigDecimal result = emissionDAO.getEmission(TRANSPORT_TYPE_ON_SYSTEM);
		Assert.assertEquals(EXPECTED_EMISSION_FOR_TRANSPORT_TYPE_ON_SYSTEM,
				result);
	}
		


	public void shouldNotFindEmission() {
		BigDecimal result = emissionDAO.getEmission(TRANSPORT_TYPE_NOT_ON_SYSTEM);
		Assert.assertEquals(null, result);
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldDealWithNull() {
		expectedEx.expect(IllegalArgumentException.class);
		emissionDAO.getEmission(null);

	}

	@Test
	public void shouldGenerateMessageWithNull() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Invalid transport type");
		emissionDAO.getEmission(null);
	}

	@Test
	public void shouldGenerateMessageWithoutDataSource() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Datasource not set");
		emissionDAO = new XMLEmissionDAO();
		// dont set datasource
		emissionDAO.getEmission(TRANSPORT_TYPE_NOT_ON_SYSTEM);
	}

}
