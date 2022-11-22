package com.coderhouse.coderhouse.document;

import com.coderhouse.coderhouse.enums.RoleEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="role")
public class Role {

    @Id
    String id;

    RoleEnum name;
}
