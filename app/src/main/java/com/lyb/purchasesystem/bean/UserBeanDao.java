package com.lyb.purchasesystem.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_BEAN".
*/
public class UserBeanDao extends AbstractDao<UserBean, Void> {

    public static final String TABLENAME = "USER_BEAN";

    /**
     * Properties of entity UserBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Token = new Property(0, String.class, "token", false, "TOKEN");
        public final static Property Email = new Property(1, String.class, "email", false, "EMAIL");
        public final static Property Phone = new Property(2, String.class, "phone", false, "PHONE");
        public final static Property Username = new Property(3, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(4, String.class, "password", false, "PASSWORD");
        public final static Property Icon = new Property(5, String.class, "icon", false, "ICON");
        public final static Property Departments = new Property(6, String.class, "departments", false, "DEPARTMENTS");
        public final static Property Power = new Property(7, String.class, "power", false, "POWER");
        public final static Property UserId = new Property(8, String.class, "userId", false, "USER_ID");
    }


    public UserBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_BEAN\" (" + //
                "\"TOKEN\" TEXT UNIQUE ," + // 0: token
                "\"EMAIL\" TEXT," + // 1: email
                "\"PHONE\" TEXT," + // 2: phone
                "\"USERNAME\" TEXT," + // 3: username
                "\"PASSWORD\" TEXT," + // 4: password
                "\"ICON\" TEXT," + // 5: icon
                "\"DEPARTMENTS\" TEXT," + // 6: departments
                "\"POWER\" TEXT," + // 7: power
                "\"USER_ID\" TEXT);"); // 8: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(1, token);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(2, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(6, icon);
        }
 
        String departments = entity.getDepartments();
        if (departments != null) {
            stmt.bindString(7, departments);
        }
 
        String power = entity.getPower();
        if (power != null) {
            stmt.bindString(8, power);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(9, userId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(1, token);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(2, email);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(3, phone);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(5, password);
        }
 
        String icon = entity.getIcon();
        if (icon != null) {
            stmt.bindString(6, icon);
        }
 
        String departments = entity.getDepartments();
        if (departments != null) {
            stmt.bindString(7, departments);
        }
 
        String power = entity.getPower();
        if (power != null) {
            stmt.bindString(8, power);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(9, userId);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public UserBean readEntity(Cursor cursor, int offset) {
        UserBean entity = new UserBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // token
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // email
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // phone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // username
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // password
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // icon
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // departments
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // power
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserBean entity, int offset) {
        entity.setToken(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setEmail(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUsername(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassword(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIcon(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDepartments(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPower(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUserId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(UserBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(UserBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(UserBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
