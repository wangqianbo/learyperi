-- 菜单
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(1,'root','/1/','',0,'root','/root/','',null);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(2,'system_config','/1/2/','',1,'系统设置','/root/系统设置/','',1);
INSERT INTO t_menu_info(MI_ID,MI_CODE,MI_ID_PATH,MI_IMG_PATH,MI_LEVEL,MI_NAME,MI_NAME_PATH,MI_URL,MI_PARENT_ID) 
	VALUES(3,'menu_config','/1/2/3','',2,'菜单设置','/root/系统设置/菜单设置','/page/menu/menu!listMenu.action',2);
commit;
