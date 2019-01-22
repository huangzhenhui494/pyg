 //控制层 
app.controller('goodsController' ,function($scope,$controller,goodsService,itemCatService){
	
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
	$scope.findOne=function(id){				
		goodsService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.id!=null){//如果有ID
			serviceObject=goodsService.update( $scope.entity ); //修改  
		}else{
			serviceObject=goodsService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
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

	// 定义封装分类名称数组
	$scope.itemCatList = [];
	// 查询商品分类
	$scope.findAllCatList = function () {
		// 调用分类服务
		itemCatService.findAll().success(function(data){
			// 循环返回的集合
			for(var i=0;i<data.length;i++){
				// 角标和角标的值
				$scope.itemCatList[data[i].id] = data[i].name;
			}

		});
	}

	// 定义商品审核状态数组
	$scope.state = ["未审核","已审核","审核未通过","关闭"];

	// 商品审核方法
	$scope.updateGoodsStatus = function (status) {
		// 调用商品的服务修改商品的审核状态
		goodsService.updateGoodsStatus($scope.selectIds,status).success(function(data){
			if(data.success){
				// 分页查询刷新方法
				$scope.reloadList();
				// 清空参数集合
				$scope.selectIds=[];
			}else{
				alert("审核失败");
			}
		})
	};

    
});	
