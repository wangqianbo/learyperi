-- 菜单
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483647,'root','/2147483647/','',0,'root','/root/','',null);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483646,'system_config','/2147483647/2147483646/','',1,'系统设置','/root/系统设置/','',2147483647);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2147483645,'menu_config','/2147483647/2147483646/2147483645/','',2,'菜单设置','/root/系统设置/菜单设置/','/page/menu/menu!listMenu.action',2147483646);
commit;