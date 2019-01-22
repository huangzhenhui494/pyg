 //控制层 
app.controller('goodsController' ,function($scope,$location,$controller,goodsService,itemCatService,uploadService,typeTemplateService){
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		goodsService.findAll().success(
			function(response){
				$scope.list=response;

			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		goodsService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(){

		// 接收参数
		// 这样既可以接收ng-click的参数,也可以接收其他页面跳转传递的参数(路由)
		var id = $location.search()["id"];
		// 判断页面传递的参数是否存在
		if(id == null){
			return;
		}
		// 根据id查询
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;
				// 回显描述信息
                editor.html($scope.entity.goodsDesc.introduction);
                // 回显图片属性
                $scope.entity.goodsDesc.itemImages =  JSON.parse($scope.entity.goodsDesc.itemImages);
                // 回显扩展属性
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse( $scope.entity.goodsDesc.customAttributeItems);
                // 回显规格属性
                $scope.entity.goodsDesc.specificationItems = JSON.parse($scope.entity.goodsDesc.specificationItems);
                // 回显规格属性选项组合sku
                for(var i=0;i<$scope.entity.itemList.length;i++){
                    $scope.entity.itemList[i].spec = JSON.parse($scope.entity.itemList[i].spec)
                }

			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.goods.id!=null){//如果有ID
			// 获取富文本编辑器的数据
			$scope.entity.goodsDesc.introduction = editor.html();
			serviceObject=goodsService.update( $scope.entity ); //修改
		}else{
			// 获取富文本编辑器的数据
			$scope.entity.goodsDesc.introduction = editor.html();
			serviceObject=goodsService.add( $scope.entity  );//增加
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					// 清空对象
					$scope.entity = {}
					// 清空富文本编辑器
					editor.html('');
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		goodsService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//定义查询节点方法
	//先查询顶级节点,默认父id=0
	//查询一级节点
	$scope.findItemCat1List  = function(){
		//调用服务方法
		itemCatService.findItemCatByParentId(0).success(function(data){
			//赋值
			$scope.itemCat1List = data;
		});
	};
	
	// 查询二级级联节点
	//$watch: 监控属性值变化,从而查询二级节点
	$scope.$watch('entity.goods.category1Id',function(newValue,oldValue){
		// 使用新的变化的值查询子节点
		// newValue就是分类id
		itemCatService.findItemCatByParentId(newValue).success(function(data){
			//赋值
			$scope.itemCat2List = data;
		});
	})
	
	// 查询三级级联节点
	//$watch: 监控属性值变化,从而查询二级节点
	$scope.$watch('entity.goods.category2Id',function(newValue,oldValue){
		// 使用新的变化的值查询子节点
		// newValue就是分类id
		itemCatService.findItemCatByParentId(newValue).success(function(data){
			//赋值
			$scope.itemCat3List = data;
		});
	})
	
	//监控第三级节点,查询模板id
	//$watch: 监控属性值变化
	$scope.$watch('entity.goods.category3Id',function(newValue,oldValue){
		itemCatService.findOne(newValue).success(function(data){
			$scope.entity.goods.typeTemplateId = data.typeId;
		});
	})


	//监控模板id变化,查询模板数据
	$scope.$watch('entity.goods.typeTemplateId',function (newValue,oldValue) {
		// 查询模板对象值
		// 调用模板服务
		typeTemplateService.findOne(newValue).success(function (data) {
			// 绑定模板对象
			$scope.typeTemplate = data;
			// 获取模板中的品牌数据
			$scope.typeTemplate.brandIds = JSON.parse($scope.typeTemplate.brandIds)

            // 判断
            // 当id不存在,即不是修改状态时,才赋值
            if($location.search()["id"] == null){
                // 获取模板中 扩展属性
                $scope.entity.goodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems)
            }

        })

		// 根据模板id查询规格属性和对应的规格选项
			// 调用模板服务的方法
			typeTemplateService.findSpecOptionListByTypeId(newValue).success(function(data){
				$scope.specList = data;
				console.log("123213213l1kjjjjjjjjjjjjjjjjjjjjjjj")
			})
	})
	
	
	// 文件上传
	$scope.uploadFile = function(){
		// 调用服务层
		uploadService.uploadFile().success(function(data){
			// 是否上传成功
			if(data.success){
				// 把图片地址获取
				$scope.imageEntity.url = data.message;
			}else{
				// 上传失败
				alert("上传失败")
			}
		}) 
	}
	
	// 定义初始化对象
	$scope.entity = {goods:{},goodsDesc:{itemImages:[],specificationItems:[]}}
	// 保存图片操作
	// 保存图片:只需要利用数据双向绑定原理,获取到图片上传数据,把数据绑定到需要保存的
	// entity实体即可
	$scope.add_image_entity = function(){
		// 把图片对象数据添加到商品描述对象
		$scope.entity.goodsDesc.itemImages.push($scope.imageEntity)
	}
	
	// 删除图片对象
	$scope.removeImageEntity = function(index){
		$scope.entity.goodsDesc.itemImages.splice(index,1)
	}

	// 定义选中规格属性事件
	$scope.updateSpecAttribute = function($event,text,name){
		// 获取实体中规格选项的值
		var specList = $scope.entity.goodsDesc.specificationItems;

		// 循环规格选项值
		for(var i=0;i<specList.length;i++){
			// 判断选择的是哪个属性
			if(specList[i].attributeName == text){
				// 判断是否选中事件
				if($event.target.checked){
					// 如果再次选中了一个,把规格选项数据结构中
					specList[i].attributeValue.push(name)
				}else{
					// 不然的话就是取消事件
					specList[i].attributeValue.splice(specList[i].attributeValue.indexOf(name),1);
				}
				// 选的就一个值,相等了就不需要再循环了
				return;
			}
		}

		// 如果商品描述中规格书型没值,把选中的值推送到集合中
		// 第一次点击规格属性选项值
		//[{"attributeName":"网络制式","attributeValue":["移动3G"]}]
		specList.push({attributeName:text,attributeValue:[name]})
	}

	// 定义函数,封装规格选项组合成商品最小销售单元
	$scope.createSkuItemList = function () {

		// 初始化规格数据组合
		$scope.entity.itemList = [{
			spec:{},
			price:99999,
			stockCount:0,
			status:'0',
			isDefault:'0'}]
		// 获取选中的规格属性的值
		// [{"attributeName":"网络","attributeValue":["电信2G","联通2G"]}
		var specList = $scope.entity.goodsDesc.specificationItems;

		// 循环规格属性值,组合sku最小销售单元商品数据
		for(var i=0;i<specList.length;i++){

			/*
			第一次循环  itemList=
			[{spec:{"网络":"电信2G"},price:99999,stockCount:0,status:'0',isDefault:'0'},
			{"网络":"联通2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}]
			*/

			/*
			第二次循环  传递 网络制式 :3g,4g

			 */

			// 添加一列
			$scope.entity.itemList = addColumn($scope.entity.itemList,specList[i].attributeName,specList[i].attributeValue);
		}
	}


	addColumn = function (list,name,values) {

		var newList = [];

		// 第一次循环数据 {spec:{},price:99999,stockCount:0,status:'0',isDefault:'0'}
		// 第二次循环数据 两个对象
		// 循环list集合数据
		for(var i=0;i<list.length;i++){
			// 第一次循环的第一个对象{spec:{},price:99999,stockCount:0,status:'0',isDefault:'0'}
			// 第二次循环的第一个对象{spec:{"网络":"电信2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}
			// 获取一个旧的对象
			var oldRow = list[i];

			// 第一次循环values["电信2G","联通2G"]
			// 第二次循环values["16G",64G]

			// 第二个循环
			for(var j=0;j<values.length;j++){
				// {spec:{},price:99999,stockCount:0,status:'0',isDefault:'0'}
				// 深克隆操作,新创建一行数据
				// 第一次的是{spec:{},price:99999,stockCount:0,status:'0',isDefault:'0'}
				// 第二次循环的是上面的list[i]即list[1]也就是{spec:{"网络":"电信2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}
				// 所以第二次会再后面再加上一个对象变成
				// {spec:{"网络":"电信2G","机身内存":"16G"},price:99999,stockCount:0,status:'0',isDefault:'0'}
				var newRow = JSON.parse(JSON.stringify(oldRow));
				newRow.spec[name] = values[j];

				// j:循环第一次{spec:{"网络":"电信2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}
				// j:循环第一次{spec:{"网络":"联通2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}
				// 推送到集合里
				newList.push(newRow);

			}
		}
		// 第一次循环,点了两个属性,就是两行
		//[{spec:{"网络":"电信2G"},price:99999,stockCount:0,status:'0',isDefault:'0'},{"网络":"联通2G"},price:99999,stockCount:0,status:'0',isDefault:'0'}]
		return newList;
	}


	// 定义状态字符串数组
	$scope.state = ["未审核","已审核","审核未通过","关闭"];

	// 定义封装分类名称数组
	$scope.itemCatList = [];

	// 查询商品分类
	$scope.findAllCatList = function () {
		// 调用分类服务
		itemCatService.findAll().success(function(data){
			var value = '';
			// 循环返回的集合
			for(var i=0;i<data.length;i++){
				// 角标和角标的值
				$scope.itemCatList[data[i].id] = data[i].name;
			}

		});
	}

	// 定义检测规格选项是否选中的函数
    // 选中的话返回true
    $scope.isAttributeChecked = function (specName,value) {
	    // 获取商品规格属性值
        var specList = $scope.entity.goodsDesc.specificationItems;
        // 循环规格属性
        for(var i=0;i<specList.length;i++){
            // 判断选择的属性是属于哪个属性分类
            if(specList[i].attributeName == specName){
                // 判断规格选项中是否包含此时选中的选项值
                if(specList[i].attributeValue.indexOf(value)>=0){
                    return true;
                }
                return false;
            }
        }


    }

});
