delete from hibernate_sequences;
delete from t_menu_info order by mi_id desc;
delete from t_user_role_rel;
delete from t_role_res_rel;
delete from t_user_info;
delete from t_org_info order by ogi_id desc;
delete from t_role_info;
delete from t_res_info;
commit;