package com.dhx.apicore.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceIdsQuery
 * @date : 2023/12/29/ 20:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterfaceIdsQuery {

    @Size
    List<Long> ids;
}
