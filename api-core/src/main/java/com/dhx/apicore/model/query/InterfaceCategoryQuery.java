package com.dhx.apicore.model.query;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceCategoryQuery
 * @date : 2023/12/26/ 16:32
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterfaceCategoryQuery extends PageQuery{

    @Size(min = 1,max = 5,message = "请求类型非法!")
    List<InterfaceCategoryEnum> categories;
}
