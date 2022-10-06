package com.sgbg.blockchain.wrapper;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Contracts_Cash_sol_Cash extends Contract {
    public static final String BINARY = "60806040526040518060400160405280600981526020017f53676267546f6b656e0000000000000000000000000000000000000000000000815250600490816200004a91906200040f565b506040518060400160405280600581526020017f5342544b4e000000000000000000000000000000000000000000000000000000815250600590816200009191906200040f565b506012600660006101000a81548160ff021916908360ff160217905550348015620000bb57600080fd5b5060405162001677380380620016778339818101604052810190620000e191906200052c565b33600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600080600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555080600381905550506200055e565b600081519050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b600060028204905060018216806200021757607f821691505b6020821081036200022d576200022c620001cf565b5b50919050565b60008190508160005260206000209050919050565b60006020601f8301049050919050565b600082821b905092915050565b600060088302620002977fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8262000258565b620002a3868362000258565b95508019841693508086168417925050509392505050565b6000819050919050565b6000819050919050565b6000620002f0620002ea620002e484620002bb565b620002c5565b620002bb565b9050919050565b6000819050919050565b6200030c83620002cf565b620003246200031b82620002f7565b84845462000265565b825550505050565b600090565b6200033b6200032c565b6200034881848462000301565b505050565b5b8181101562000370576200036460008262000331565b6001810190506200034e565b5050565b601f821115620003bf57620003898162000233565b620003948462000248565b81016020851015620003a4578190505b620003bc620003b38562000248565b8301826200034d565b50505b505050565b600082821c905092915050565b6000620003e460001984600802620003c4565b1980831691505092915050565b6000620003ff8383620003d1565b9150826002028217905092915050565b6200041a8262000195565b67ffffffffffffffff811115620004365762000435620001a0565b5b620004428254620001fe565b6200044f82828562000374565b600060209050601f83116001811462000487576000841562000472578287015190505b6200047e8582620003f1565b865550620004ee565b601f198416620004978662000233565b60005b82811015620004c1578489015182556001820191506020850194506020810190506200049a565b86831015620004e15784890151620004dd601f891682620003d1565b8355505b6001600288020188555050505b505050505050565b600080fd5b6200050681620002bb565b81146200051257600080fd5b50565b6000815190506200052681620004fb565b92915050565b600060208284031215620005455762000544620004f6565b5b6000620005558482850162000515565b91505092915050565b611109806200056e6000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c806340c10f191161007157806340c10f191461017a5780635c6581651461019657806370a08231146101c657806395d89b41146101f6578063a9059cbb14610214578063dd62ed3e14610244576100a9565b806306fdde03146100ae578063095ea7b3146100cc57806323b872dd146100fc57806327e235e31461012c578063313ce5671461015c575b600080fd5b6100b6610274565b6040516100c39190610bfa565b60405180910390f35b6100e660048036038101906100e19190610cb5565b610302565b6040516100f39190610d10565b60405180910390f35b61011660048036038101906101119190610d2b565b6103f4565b6040516101239190610d10565b60405180910390f35b61014660048036038101906101419190610d7e565b6106da565b6040516101539190610dba565b60405180910390f35b6101646106f2565b6040516101719190610df1565b60405180910390f35b610194600480360381019061018f9190610cb5565b610705565b005b6101b060048036038101906101ab9190610e0c565b61084c565b6040516101bd9190610dba565b60405180910390f35b6101e060048036038101906101db9190610d7e565b610871565b6040516101ed9190610dba565b60405180910390f35b6101fe6108b9565b60405161020b9190610bfa565b60405180910390f35b61022e60048036038101906102299190610cb5565b610947565b60405161023b9190610d10565b60405180910390f35b61025e60048036038101906102599190610e0c565b610ae3565b60405161026b9190610dba565b60405180910390f35b6004805461028190610e7b565b80601f01602080910402602001604051908101604052809291908181526020018280546102ad90610e7b565b80156102fa5780601f106102cf576101008083540402835291602001916102fa565b820191906000526020600020905b8154815290600101906020018083116102dd57829003601f168201915b505050505081565b600081600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040516103e29190610dba565b60405180910390a36001905092915050565b600080600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050826000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054101580156104c45750828110155b610503576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104fa90610f1e565b60405180910390fd5b826000808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546105519190610f6d565b92505081905550826000808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546105a69190610fa1565b925050819055507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8110156106695782600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546106619190610fa1565b925050819055505b8373ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef856040516106c69190610dba565b60405180910390a360019150509392505050565b60006020528060005260406000206000915090505481565b600660009054906101000a900460ff1681565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1603610774576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161076b90611021565b60405180910390fd5b80600360008282546107869190610f6d565b92505081905550806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282546107db9190610f6d565b925050819055503373ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040516108409190610dba565b60405180910390a35050565b6001602052816000526040600020602052806000526040600020600091509150505481565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b600580546108c690610e7b565b80601f01602080910402602001604051908101604052809291908181526020018280546108f290610e7b565b801561093f5780601f106109145761010080835404028352916020019161093f565b820191906000526020600020905b81548152906001019060200180831161092257829003601f168201915b505050505081565b6000816000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410156109ca576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109c1906110b3565b60405180910390fd5b816000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610a189190610fa1565b92505081905550816000808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254610a6d9190610f6d565b925050819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef84604051610ad19190610dba565b60405180910390a36001905092915050565b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b600081519050919050565b600082825260208201905092915050565b60005b83811015610ba4578082015181840152602081019050610b89565b60008484015250505050565b6000601f19601f8301169050919050565b6000610bcc82610b6a565b610bd68185610b75565b9350610be6818560208601610b86565b610bef81610bb0565b840191505092915050565b60006020820190508181036000830152610c148184610bc1565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610c4c82610c21565b9050919050565b610c5c81610c41565b8114610c6757600080fd5b50565b600081359050610c7981610c53565b92915050565b6000819050919050565b610c9281610c7f565b8114610c9d57600080fd5b50565b600081359050610caf81610c89565b92915050565b60008060408385031215610ccc57610ccb610c1c565b5b6000610cda85828601610c6a565b9250506020610ceb85828601610ca0565b9150509250929050565b60008115159050919050565b610d0a81610cf5565b82525050565b6000602082019050610d256000830184610d01565b92915050565b600080600060608486031215610d4457610d43610c1c565b5b6000610d5286828701610c6a565b9350506020610d6386828701610c6a565b9250506040610d7486828701610ca0565b9150509250925092565b600060208284031215610d9457610d93610c1c565b5b6000610da284828501610c6a565b91505092915050565b610db481610c7f565b82525050565b6000602082019050610dcf6000830184610dab565b92915050565b600060ff82169050919050565b610deb81610dd5565b82525050565b6000602082019050610e066000830184610de2565b92915050565b60008060408385031215610e2357610e22610c1c565b5b6000610e3185828601610c6a565b9250506020610e4285828601610c6a565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680610e9357607f821691505b602082108103610ea657610ea5610e4c565b5b50919050565b7f746f6b656e2062616c616e6365206f7220616c6c6f77616e6365206973206c6f60008201527f776572207468616e20616d6f756e742072657175657374656400000000000000602082015250565b6000610f08603983610b75565b9150610f1382610eac565b604082019050919050565b60006020820190508181036000830152610f3781610efb565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610f7882610c7f565b9150610f8383610c7f565b9250828201905080821115610f9b57610f9a610f3e565b5b92915050565b6000610fac82610c7f565b9150610fb783610c7f565b9250828203905081811115610fcf57610fce610f3e565b5b92915050565b7f6d696e7420746f207a65726f2061646472657373000000000000000000000000600082015250565b600061100b601483610b75565b915061101682610fd5565b602082019050919050565b6000602082019050818103600083015261103a81610ffe565b9050919050565b7f746f6b656e2062616c616e6365206973206c6f776572207468616e207468652060008201527f76616c7565207265717565737465640000000000000000000000000000000000602082015250565b600061109d602f83610b75565b91506110a882611041565b604082019050919050565b600060208201905081810360008301526110cc81611090565b905091905056fea2646970667358221220344f17722362a8a2aa1642953da841de8b4bf74a38af5930c892e4d5e263c9b664736f6c63430008110033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_ALLOWED = "allowed";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Contracts_Cash_sol_Cash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_Cash_sol_Cash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_Cash_sol_Cash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_Cash_sol_Cash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse._owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse._value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String _owner, String _spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new Address(160, _owner),
                new Address(160, _spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> allowed(String param0, String param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWED, 
                Arrays.<Type>asList(new Address(160, param0),
                new Address(160, param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(160, _spender),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, _owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> balances(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new Address(160, param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String _receiver, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Address(160, _receiver),
                new Uint256(_amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Address(160, _to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(160, _from),
                new Address(160, _to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_Cash_sol_Cash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Cash_sol_Cash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_Cash_sol_Cash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Cash_sol_Cash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_Cash_sol_Cash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_Cash_sol_Cash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_Cash_sol_Cash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_Cash_sol_Cash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_Cash_sol_Cash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _initialAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount)));
        return deployRemoteCall(Contracts_Cash_sol_Cash.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Contracts_Cash_sol_Cash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _initialAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount)));
        return deployRemoteCall(Contracts_Cash_sol_Cash.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_Cash_sol_Cash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount)));
        return deployRemoteCall(Contracts_Cash_sol_Cash.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Contracts_Cash_sol_Cash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _initialAmount) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(_initialAmount)));
        return deployRemoteCall(Contracts_Cash_sol_Cash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String _owner;

        public String _spender;

        public BigInteger _value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String _from;

        public String _to;

        public BigInteger _value;
    }
}
