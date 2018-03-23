/**
 * 
 */
package com.fancye.wx.comm.controller;

import com.jfinal.core.Controller;

/**
 * @author Fancye
 *
 */
public class IndexController extends Controller {

	public void index() {
		renderText("Hello Fancye.");
	}
}
