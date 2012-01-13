-- 默认菜单
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_ORDER,MI_TYPE,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483647,'root_menu','/2147483647/','',0,0,99,'root_menu','/root_menu/','',null);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_ORDER,MI_TYPE,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483646,'system_config','/2147483647/2147483646/','',1,100,99,'系统设置','/root_menu/系统设置/','',2147483647);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_ORDER,MI_TYPE,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483645,'menu_config','/2147483647/2147483646/2147483645/','',2,100,0,'菜单设置','/root_menu/系统设置/菜单设置/','/page/menu/menu!listMenu.action',2147483646);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_ORDER,MI_TYPE,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID)
	VALUES(2147483644,'user_logout','/2147483647/2147483644/','',1,99999,1,'登出','/root_menu/登出/','/j_spring_security_logout',2147483647);
-- 默认管理员角色、用户和组织结构
INSERT INTO T_ORG_INFO(OGI_ID,OGI_CODE,OGI_NAME,OGI_LEVEL,OGI_ORDER,OGI_DESC,OGI_ID_PATH,OGI_NAME_PATH,OGI_PARENT_ID)
	VALUES(2147483647,'root_org','root_org',0,0,'虚拟的组织结构根节点','/2147483647/','/root_org/',null);
INSERT INTO T_ROLE_INFO(ROI_ID,ROI_CODE,ROI_NAME,ROI_DESC)
	VALUES(2147483647,'role_admin','超级管理员角色','超级管理员角色');
INSERT INTO T_USER_INFO(USI_ID,USI_NAME,USI_PASS,USI_FIRST_NAME,USI_LAST_NAME,OGI_ID)
	VALUES(2147483647,'admin','learyperi','','超级管理员',2147483647);
INSERT INTO T_RES_INFO(RSI_ID,RSI_URL) VALUES(2147483647,'/');
INSERT INTO T_RES_INFO(RSI_ID,RSI_URL) VALUES(2147483646,'/page');
INSERT INTO T_ROLE_RES_REL(ROI_ID,RSI_ID) VALUES(2147483647,2147483647);
INSERT INTO T_USER_ROLE_REL(USI_ID,ROI_ID) VALUES(2147483647,2147483647);
commit;