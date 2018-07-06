package com.es.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.es.pojo.Admin;
import com.es.pojo.Goods;
import com.es.pojo.User;
import com.es.service.AdminService;
import com.es.service.GoodsService;
import com.es.service.UserService;

@RequestMapping("manage/")
@Controller
public class ManageController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@RequestMapping("login.html")
	public String manageLogin() {
		return "manage/login";
	}

	@RequestMapping("index.html")
	public String manageIndex() {
		return "manage/index";
	}

	@RequestMapping("goods-list")
	public String findAllGoods(Model model) {
		List<Goods> goodsList = goodsService.findManageGoods();
		Integer count = goodsService.findCount();
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("count", count);
		return "manage/goods-list";
	}
	/**
	 * 显示所有会员信息
	 * @param model
	 * @return 会员列表页面
	 */
	@RequestMapping("member-list")
	public String findAllUser(Model model) {
		List<User> userList = userService.findAllUser();
		Integer count = userService.findCount();
		model.addAttribute("userList", userList);
		model.addAttribute("count", count);
		return "manage/member-list";
	}
	/**
	 * 更新会员状态
	 * @param id 会员id
	 * @param state -1 0 1 分别表示删除、停用、启用
	 * @return json状态码 0失败 1成功
	 */
	@ResponseBody
	@RequestMapping("updateUserState")
	public String updateUserState(String id, Integer state) {
		return userService.updateUser(id, state);
	}
	/**
	 * 查询单个会员信息
	 * @param id 会员id
	 * @param model
	 * @return 会员信息修改页面
	 */
	@RequestMapping("member-add/{id}")
	public String queryUser(@PathVariable String id, Model model) {
		User user = userService.queryUser(id);
		model.addAttribute("user", user);
		return "manage/member-add";
	}
	/**
	 * 修改会员信息
	 * @param id 会员id
	 * @param password 密码
	 * @param phone 手机号
	 * @param remark 备注
	 * @param model
	 * @return 重定向到会员列表页面
	 */
	@RequestMapping("member-add/updateUser")
	public String updateUser(String id, String password, String phone, String remark, Model model) {
		userService.updateUser(id, password, phone, remark);
		return "redirect:member-list.html";
	}
	
	@RequestMapping("goods-alter/{id}")
	public String queryGoods(@PathVariable String id, Model model) {
		Goods goods = goodsService.queryGoods(id);
		model.addAttribute("goods", goods);
		return "manage/goods-alter";
	}

	@ResponseBody
	@RequestMapping("goods-alter/updateGoods")
	public String updateGoods(String id, String category, String title, String price, String des, String image,
			Model model) {
		String json = goodsService.updateGoods(id, category, title, price, des, image);
		return json;
	}

	@ResponseBody
	@RequestMapping("updateGoodsState")
	public String updateGoodsState(String id, Integer state) {
		return goodsService.updateGoods(id, state);
	}

	@RequestMapping("goods-add")
	public String addGoods() {
		return "manage/goods-add";
	}

	@ResponseBody
	@RequestMapping("insertGoods")
	public String insertGoods(String category, String title, String price, String des, String image, Model model) {
		String json = goodsService.insertGoods(category, title, price, des, image);
		return json;
	}
	
	/**
	 * 登录验证
	 * @param username 用户名
	 * @param password 密码
	 * @param session 服务器Session
	 * @return json转态码 0失败 1成功
	 */
	@ResponseBody
	@RequestMapping("checkLogin.html")
	public String checkLogin(String username, String password, HttpSession session) {
		String json = adminService.checkLogin(username, password, session);
		return json;
	}
	/**
	 * 注销
	 * @param session
	 * @return 登录页面
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("adminName");
		session.removeAttribute("adminId");
		session.removeAttribute("adminRole");
		return "manage/login";
	}
	/**
	 * 图片上传
	 * @param request
	 * @return 返回图片服务器地址
	 */
	@ResponseBody
	@RequestMapping(value = {"uploadImage","goods-alter/uploadImage"})
	public String uploadImage(HttpServletRequest request) {
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
		String dir = "d://easyshop/image/";
		String imageName = UUID.randomUUID().toString() + ".jpg";
		String filename = dir + imageName;
		String image = "http://image.easyshop.com/" + imageName;
		Map<String, MultipartFile> files = mulRequest.getFileMap();
		for (MultipartFile file : files.values()) {
			InputStream inputStream = null;
			FileOutputStream fileOutputStream = null;
			try {
				inputStream = file.getInputStream();
				fileOutputStream = new FileOutputStream(new File(filename));
				byte[] buffer = new byte[102400];
				int length = 0;
				while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
					fileOutputStream.write(buffer, 0, length);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileOutputStream != null) {
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						fileOutputStream = null;
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						inputStream = null;
					}
				}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("image", image);
		String json = JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping("admin-list")
	public String findAllAdmin(Model model) {
		List<Admin> adminList = adminService.findAllAdmin();
		Integer count = adminService.findCount();
		model.addAttribute("adminList", adminList);
		model.addAttribute("count", count);
		return "manage/admin-list";
	}
	
	@RequestMapping("admin-add")
	public String addAdmin() {
		return "manage/admin-add";
	}
	
	@ResponseBody
	@RequestMapping("checkName.html")
	public String checkName(String username) {
		String flag = adminService.checkName(username);
		return flag;
	}
	
	@ResponseBody
	@RequestMapping("updateAdminState")
	public String updateAdminState(String id, Integer state) {
		return adminService.updateAdminState(id, state);
	}

	@RequestMapping("admin-alter/{id}")
	public String queryAdmin(@PathVariable String id, Model model) {
		Admin admin = adminService.queryAdmin(id);
		model.addAttribute("admin", admin);
		return "manage/admin-alter";
	}

	@RequestMapping("admin-alter/updateAdmin")
	public String updateAdmin(String id, String username, String password, String phone, String role, String remark, Model model) {
		adminService.updateAdmin(id, password, phone, role, remark);
		return "redirect:admin-list.html";
	}
	
	@ResponseBody
	@RequestMapping("insertAdmin")
	public String insertAdmin(String username, String password, String phone, String role, String remark) {
		String json = adminService.insertAdmin(username,password,phone,role,remark);
		return json;
	}

}
