/**
 * 
 */
package com.mahmud.widespacesdktest.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import com.mahmud.widespacesdktest.Provisioner;
import junit.framework.TestCase;

/**
 * This is test case generate class for Provision class.To generate test case
 * method it extend the TestCase class.This class working with
 * com.mahmud.widespacesdktest this package application file(apk).
 * 
 * @author Mahmud
 * 
 */
public class TestProvisioner extends TestCase {
	/**
	 * Get provision class instance though this class is single tone class.
	 */
	private Provisioner provision = Provisioner.getProvisionerInstance();
	/**
	 * Method class used to generate test case on any private class.Here we use
	 * three private method getServerUrl,sendHttpRequest,storeKey,parseProvisioningReply
	 */
	private Method getServerUrlMethod, sendHttpRequestMethod, storeKeyMethod,parseProvisioningReplyMethod;
	private static String METHOD_NAME = "getServerUrl";
	private static String METHOD_NAME2 = "sendHttpRequest";
	private static String METHOD_NAME3 = "storeKey";
	private static String METHOD_NAME4 = "parseProvisioningReply";
	/**
	 * Class use to rawclass type of method perameter.like if any method
	 * Parameter is string type then this Class should be java.lang.String.class
	 */
	private Class[] getServerUrlMethodParameterTypes,
			sendHttpRequestMethodParameterTypes, storeKeyParameterTypes;
	/**
	 * Object use to pass the method perameter.like if any method have two
	 * parameter it's index by [0],[1]
	 */
	private Object[] getServerUrlMethodParameter,
			sendHttpRequestMethodParameter, storeKeyParameter;
	// This is our test url
	private static String URl = "http://engine.widespace.com/map/provisioning?appId=com.widespace.wp.testapp&sdkVer=4.1.0&platform=ANDROID";
	// Test key for store key method
	private static final String IS_SDK_ENABLED = "is_sdk_enable";
	// Hashmap use to show the readKey method output
	HashMap<String, Object> mapStoreKey = new HashMap<String, Object>();

	/**
	 * This setUp method used to initialize all object for private methods test
	 * case.To Generate private method test case we using Reflection mechanism
	 */

	protected void setUp() throws Exception {
		// initialize for getServerUrl private method
		getServerUrlMethodParameterTypes = new Class[1];
		getServerUrlMethodParameterTypes[0] = java.lang.String.class;
		getServerUrlMethodParameter = new Object[1];
		getServerUrlMethod = provision.getClass().getDeclaredMethod(
				METHOD_NAME, getServerUrlMethodParameterTypes);
		getServerUrlMethod.setAccessible(true);

		// initialize for sendHttpRequest private method
		sendHttpRequestMethodParameterTypes = new Class[1];
		sendHttpRequestMethodParameterTypes[0] = java.lang.String.class;
		sendHttpRequestMethodParameter = new Object[1];
		sendHttpRequestMethod = provision.getClass().getDeclaredMethod(
				METHOD_NAME2, sendHttpRequestMethodParameterTypes);
		sendHttpRequestMethod.setAccessible(true);

		// initialize for storeKey private method
		storeKeyParameterTypes = new Class[2];
		storeKeyParameterTypes[0] = java.lang.String.class;
		storeKeyParameterTypes[1] = java.lang.Object.class;
		storeKeyParameter = new Object[2];
		storeKeyMethod = provision.getClass().getDeclaredMethod(METHOD_NAME3,
				storeKeyParameterTypes);
		storeKeyMethod.setAccessible(true);
		//initialize for parsing the server response data
		parseProvisioningReplyMethod = provision.getClass().getDeclaredMethod(METHOD_NAME4,
				getServerUrlMethodParameterTypes);
		parseProvisioningReplyMethod.setAccessible(true);

	}

	/**
	 * This testProvision public method using to test Provision method in
	 * provisioner class. By this test case we check the provision
	 * status.compare with this provision class instance. By default it's
	 * provision status was UNPROVISIONED so that we check with this status and
	 * after get The Server Url parse by uri builder.If this result was null it
	 * pass the status as FAILED.
	 * 
	 * {@link com.mahmud.widespacesdktest.Provisioner#Provision()}
	 */

	public void testProvision() {
		assertEquals(Provisioner.provisionStatus.UNPROVISIONED,
				provision.provisionStatus);
	}

	/**
	 * This readKey public method use after store the value/object by storeKey
	 * method. In here we pass one store key value of IS_SDK_ENABLED.If this
	 * method workes fine we saw this value is equals.
	 * {@link com.mahmud.widespacesdktest.Provisioner#readKey()}
	 */

	public void testReadKey() {

		assertEquals("sdkenable", provision.readKey(IS_SDK_ENABLED));

	}

	/**
	 * This getServerUrl private method, After getting url parsing via URI
	 * builder.By this method it check the result url is same with our constant
	 * url.If both are same it return true.It's also pass exception if any
	 * exception occured
	 * {@link com.mahmud.widespacesdktest.Provisioner#getServerUrl()}
	 * 
	 * @throws Exception
	 */

	public void testgetServerUrl() throws Exception {
		getServerUrlMethodParameter[0] = URl;
		String result = (String) getServerUrlMethod.invoke(provision,
				getServerUrlMethodParameter);
		assertTrue("Correct URL", result.contains(URl));

	}

	/**
	 * This HTTPRequest private method using to send http request to server by
	 * this url.If it working fine it's return the Provision status as
	 * IN_PROGRESS.If any exception occuredit's return the status as FAILED.
	 * {@link com.mahmud.widespacesdktest.Provisioner#sendHttpRequest()}
	 * 
	 * @throws Exception
	 */

	public void testHTTPRequest() throws Exception {

		sendHttpRequestMethodParameter[0] = URl;

		String result = (String) sendHttpRequestMethod.invoke(provision,
				sendHttpRequestMethodParameter);
		System.out.println(result + "");
		assertEquals(Provisioner.provisionStatus.IN_PROGRESS,
				provision.provisionStatus);

	}

	/**
	 * This parseProvisioningReply private method to parse the server response
	 * data.Here we checked the provisioning status after parse the data.It's
	 * showing DONE if the parse is complete.otherwise it's showing the FAILED status
	 * {@link com.mahmud.widespacesdktest.Provisioner#parseProvisioningReply()}
	 * @throws Exception
	 */
	public void testParseProvisioningReply() throws Exception {

		getServerUrlMethodParameter[0] = URl;

		String result = (String) parseProvisioningReplyMethod.invoke(provision,
				getServerUrlMethodParameter);
		System.out.println(result + "");
		assertEquals(Provisioner.provisionStatus.DONE,
				provision.provisionStatus);

	}

	/**
	 * This StoreKey private method using after parsing the Json data it store
	 * the value as key value pair in hashmap.Here we manually enter some key
	 * value and check this stored or not.We are trying to store a value in a
	 * key IS_SDK_ENABLED.If it successfully store we saw the message
	 * {@link com.mahmud.widespacesdktest.Provisioner#storeKey()}
	 * 
	 * @throws Exception
	 */

	public void testStoreKey() throws Exception {

		Object value = (Object) "sdkenable";
		storeKeyParameter[0] = IS_SDK_ENABLED;
		storeKeyParameter[1] = value;

		Object result = storeKeyMethod.invoke(provision, storeKeyParameter);

		assertNotNull("It's store value", result);
	}

}
